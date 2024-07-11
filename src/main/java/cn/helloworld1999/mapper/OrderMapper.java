package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.Order;
import cn.helloworld1999.bean.User;

import java.util.List;

public interface OrderMapper {
    int createOrder(Order order);

    int deleteOrder(int id);
    int updateOrder(Order order);
    List<Order> getSomeOrders(User user);
    /**
     * 拿回自增主键
     * @param order 插进去的Order
     * @return 自增主键
     */
    Integer creatAndReturnOrderId(Order order);
    Integer getMaxOrderId();
}
