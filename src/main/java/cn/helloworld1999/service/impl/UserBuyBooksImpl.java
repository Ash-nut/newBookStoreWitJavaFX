package cn.helloworld1999.service.impl;

import cn.helloworld1999.bean.*;
import cn.helloworld1999.service.UserBuyBooks;
import cn.helloworld1999.util.GetMapper;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static cn.helloworld1999.util.GetMapper.*;

public class UserBuyBooksImpl implements UserBuyBooks {
    /**
     * 获取所有的书籍信息
     */
    @Override
    public void showBooksList() {
        List<Book> allBook = GetMapper.getBookMapper().selectAllBook();
    }

    /**
     * 创建子订单
     *
     * @param user    谁创建的
     * @param book    商品是啥
     * @param flag    是加入购物车（1）还是准备生成订单（2）
     * @param bookNum 操作几本书？
     */
    @Override
    public void createOrderSubpage(User user, Book book, Integer flag, Integer bookNum) {
        String OrderSubpageState = "初始化";
        if (flag == 1) OrderSubpageState = "加入购物车";
        if (flag == 2) OrderSubpageState = "待生成订单";
        OrderSubpage orderSubpage = new OrderSubpage(user.getUserId(), null, book.getBookName(), bookNum, OrderSubpageState, book.getPrice(), null, book.getBookId());
        orderSubpage.toString();
        getOrderSubpageMapper().insert(orderSubpage);     //子订单存入数据库
        GetMapper.commit();
    }


    /**
     * 拿到某人的购物车
     *
     * @param user 某人
     */
    @Override
    public void showShoppingCart(User user) {
        List<OrderSubpage> userAllOrderSubpage = getOrderSubpageMapper().selectAllByUser(user);
        List<OrderSubpage> shopCar = null;
        for (OrderSubpage orderSubpage : userAllOrderSubpage) {
            if (orderSubpage.getState().equals("加入购物车")) {
                shopCar.add(orderSubpage);
            }
        }
    }

    @Override
    public void createOrder(User user) {
        // 准备生成订单
        Order order = new Order();
        //绑定给某人
        order.setUserId(user.getUserId());
        //Assert.assertEquals(user.getUserId(), order.getUserId());
//-------------------------------------------------------------------------------------------------/
        //拿到自增主键，orderId
        GetMapper.getOrderMapper().createOrder(order);
        Integer orderId = getOrderMapper().getMaxOrderId();
        GetMapper.commit();
        //Integer x = 51;
        //Assert.assertEquals(x,orderId);
//-------------------------------------------------------------------------------------------------/
        order.setOrderId(orderId);
        Double orderPriceSum = 0.0;
        //拿到子订单表
        List<OrderSubpage> userAllOrderSubpage = getOrderSubpageMapper().selectAllByUser(user);
        //Assert.assertNotNull(userAllOrderSubpage);
//-------------------------------------------------------------------------------------------------/
        //给需要生成订单的子订单赋予 orderID
        for (OrderSubpage orderSubpage : userAllOrderSubpage) {
            //筛选目标
            if (orderSubpage.getState().equals("待生成订单")) {
                //确认库存
                Book bk = new Book();
                bk.setBookId(orderSubpage.getBookId()); //装 bookId 的工具人，魔法值
                //Assert.assertNotNull(bk);
//-------------------------------------------------------------------------------------------------/
                List<Book> lb = GetMapper.getBookMapper().selectBookSelective(bk);
                //Assert.assertNotNull(lb);
                //Assert.assertEquals(1, lb.size());
                //Assert.assertEquals("计算机视觉",lb.get(0).getBookName());
//-------------------------------------------------------------------------------------------------/
                //大于零说明商品里 还有这本书
                if (lb.size() > 0) {
                    // 与库存数量进行比对,如果库存小于订单量
                    if (lb.get(0).getStock() < orderSubpage.getNum()) {
                        //给予这一项子订单全部库存
                        orderSubpage.setNum(lb.get(0).getStock());
                        //库存清零
                        lb.get(0).setStock(0);
                        //重算子订单总价
                        orderSubpage.setPriceSum(lb.get(0).getPrice() * orderSubpage.getNum());
                        Book bbk = new Book();
                        //更新数据库
                        getBookMapper().updateBook(lb.get(0));
                        GetMapper.commit();
                        orderSubpage.setInfo("库存不足，已提供最大数量");
                        // 关联到主订单了
                        orderSubpage.setOrderId(orderId);
                        getOrderSubpageMapper().updateByOrderSubpageId(orderSubpage);
                        orderSubpage.setState(OrderSubpage.STATE[3]);
                        GetMapper.commit();
                    } else {
                        lb.get(0).setStock(lb.get(0).getStock() - orderSubpage.getNum());
                        getBookMapper().updateBook(lb.get(0)); //减少库存
                        orderPriceSum += orderSubpage.getPriceSum();
                        orderSubpage.setOrderId(orderId);
                        //Integer x = 71;
                        //Assert.assertEquals(x,orderSubpage.getNum());
                        orderSubpage.setState(OrderSubpage.STATE[3]);
                        getOrderSubpageMapper().updateByOrderSubpageId(orderSubpage);
                        GetMapper.commit();
                    }
                } else {
                    System.out.println("这本书下架了");
                    order.setRemark("部分商品已下架");
                }
            }
        }
        order.setOrderSumPrice(orderPriceSum); // 设置订单总价
        order.setState(Order.STATE[0]); //设置订单状态为"待付款"
        order.setOrderDate(new Date()); //生成订单时间
        GetMapper.getOrderMapper().updateOrder(order); //把待付款订单写入
        GetMapper.commit();
    }

    /**
     * 拿到某人的所有订单
     *
     * @param user 某人
     */
    @Override
    public void showOrderList(User user) {
        List<Order> userOrder = GetMapper.getOrderMapper().getSomeOrders(user);
    }

    /**
     * 支付
     *
     * @param user  某人
     * @param order 订单
     */
    @Override
    public void pay(User user, Order order) {
        if (user.getBalance() > order.getOrderSumPrice()) {
            user.setBalance(user.getBalance() - order.getOrderSumPrice());
            GetMapper.getUserMapper().updateUserSelective(user);
            GetMapper.commit();
            order.setState(Order.STATE[2]);
            GetMapper.getOrderMapper().updateOrder(order);
            commit();
            // 商家加钱
            Role r = new Role(null, Role.ROLE_LIST[1]);
            List<Role> rl = GetMapper.getRoleMapper().selectSomeRole(r);
            User user1 = new User();
            user1.setUserId(rl.get(0).getUserId()); //为什么这么笃定这人卖的书，很简单，因为我这个系统就一个商家，新华文轩APP hahaha
            user1.setBalance(user1.getBalance() + order.getOrderSumPrice());
            getUserMapper().updateUserSelective(user1);
            GetMapper.commit();
        } else {
            order.setState(Order.STATE[1]);
            order.setRemark("余额不足，付款失败");
            getOrderMapper().updateOrder(order);
            commit();
        }
    }

    /**
     * 充值
     *
     * @param user 某人
     */
    @Override
    public void topUp(User user, Double cheangeMoney) {
        cheangeMoney = Math.abs(cheangeMoney);
        user.setBalance(user.getBalance() + cheangeMoney);
        GetMapper.getUserMapper().updateUserSelective(user);
        GetMapper.commit();
    }
}
