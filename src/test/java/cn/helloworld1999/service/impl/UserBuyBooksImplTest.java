package cn.helloworld1999.service.impl;

import cn.helloworld1999.bean.Book;
import cn.helloworld1999.bean.Order;
import cn.helloworld1999.bean.OrderSubpage;
import cn.helloworld1999.bean.User;
import cn.helloworld1999.util.GetMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static cn.helloworld1999.util.GetMapper.*;

public class UserBuyBooksImplTest {

    @Test
    public void createOrder() {
        User user = new User(31, "hahaha2333", "123456", 1024.0, 0, "19961412333");
        // 准备生成订单
        Order order = new Order();
        //绑定给某人
        order.setUserId(user.getUserId());
        Assert.assertEquals(user.getUserId(), order.getUserId());
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
        Assert.assertNotNull(userAllOrderSubpage);
//-------------------------------------------------------------------------------------------------/
        //给需要生成订单的子订单赋予 orderID
        for (OrderSubpage orderSubpage : userAllOrderSubpage) {
            //筛选目标
            if (orderSubpage.getState().equals("待生成订单")) {
                //确认库存
                Book bk = new Book();
                bk.setBookId(orderSubpage.getBookId()); //装 bookId 的工具人，魔法值
                Assert.assertNotNull(bk);
//-------------------------------------------------------------------------------------------------/
                List<Book> lb = GetMapper.getBookMapper().selectBookSelective(bk);
                Assert.assertNotNull(lb);
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
        order.setOrderTime(new Date()); //生成订单时间
        GetMapper.getOrderMapper().updateOrder(order); //把待付款订单写入
        GetMapper.commit();
    }
}
