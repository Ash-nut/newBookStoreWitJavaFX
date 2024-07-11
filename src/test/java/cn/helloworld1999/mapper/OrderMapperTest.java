package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.User;
import cn.helloworld1999.util.GetMapper;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderMapperTest {

    @Test
    public void getSomeOrders() {
        User user = new User();
        user.setUserId(31);
        Assert.assertEquals(2, GetMapper.getOrderMapper().getSomeOrders(user).size());
    }

    @Test
    public void getMaxOrderId() {
        Integer maxOrderId = GetMapper.getOrderMapper().getMaxOrderId();
        Assert.assertNotNull(maxOrderId);
        Integer want = 50;
        Assert.assertEquals(want,maxOrderId);
    }
}