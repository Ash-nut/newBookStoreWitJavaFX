package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.Order;
import cn.helloworld1999.bean.OrderSubpage;
import cn.helloworld1999.bean.User;

import java.util.List;

public interface OrderSubpageMapper {
    /**
     * 创建子订单
     * @param orderSubpage 子订单
     * @return 影响行数
     */
    int insert(OrderSubpage orderSubpage);

    /**
     * 删除子订单
     * @param orderSubpage 待删除的子订单对象
     * @return 影响行数
     */
    int deleteByOrderSubpageId(OrderSubpage orderSubpage);

    /**
     * 筛选出某人的全部子订单
     * @param user User
     * @return 子订单列表
     */
    List<OrderSubpage> selectAllByUser(User user);


    /**
     * 拿到某个子订单的数据
     * @param orderSubpage 子订单
     * @return 子订单
     */
    OrderSubpage selectByOrderSubpageId(OrderSubpage orderSubpage);

    /**
     * 修改子订单，修改内容为： state num priceOne priceSum
     * @param orderSubpage 内存里修改好子订单
     * @return 影响行数
     */
    int updateByOrderSubpageId(OrderSubpage orderSubpage);
    List<OrderSubpage> getAllOrderSubpageByOrder(Order order);
}
