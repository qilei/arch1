package me.lei.arch1.customermgr.dao;

import me.lei.arch1.customermgr.vo.CustomerModel;
import me.lei.arch1.customermgr.vo.CustomerQueryModel;
import me.lei.pagination.dto.PageMyBatis;
import me.lei.pagination.dto.datatables.BasePageCriteria;
import me.lei.pagination.dto.datatables.PagingCriteria;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by qilei on 2015/1/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDAOTest {
    @Resource
    private CustomerDAO customerDAO;

    @Test
    public void testGetByCustomerId() throws Exception {
        CustomerModel customerModel = customerDAO.getByCustomerId("c1");
        Assert.assertTrue(customerModel!=null);
    }

    @Test
    public void getByPage() throws Exception {
        PagingCriteria baseCriteria = PagingCriteria.createCriteria(0, 10, 1);
        CustomerQueryModel qm = new CustomerQueryModel(baseCriteria);
        PageMyBatis<CustomerModel> list = customerDAO.getByPage(qm);
        Assert.assertTrue(list.size()>0);
    }
}
