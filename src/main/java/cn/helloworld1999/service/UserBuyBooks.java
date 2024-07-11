package cn.helloworld1999.service;

import cn.helloworld1999.bean.Book;
import cn.helloworld1999.bean.Order;
import cn.helloworld1999.bean.OrderSubpage;
import cn.helloworld1999.bean.User;

public interface UserBuyBooks {
    void showBooksList();
    void createOrderSubpage(User user,Book book,Integer flag,Integer bookNum);
    void showShoppingCart(User user);
    void createOrder(User user);
    void showOrderList(User user);
    void pay(User user, Order order);
    void topUp(User user,Double cheangeMoney);
}
