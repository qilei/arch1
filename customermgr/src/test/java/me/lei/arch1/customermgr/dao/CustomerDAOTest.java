package me.lei.arch1.customermgr.dao;

import me.lei.arch1.customermgr.vo.CustomerModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by qilei on 2015/1/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "")
public class CustomerDAOTest {
    @Resource
    private CustomerDAO customerDAO;

    @Test
    public void testGetByCustomerId() throws Exception {
        CustomerModel customerModel = customerDAO.getByCustomerId("c1");
    }
}
