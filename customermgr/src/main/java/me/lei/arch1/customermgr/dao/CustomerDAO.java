package me.lei.arch1.customermgr.dao;

import me.lei.arch1.common.dao.BaseDAO;
import me.lei.arch1.customermgr.vo.CustomerModel;
import me.lei.arch1.customermgr.vo.CustomerQueryModel;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends BaseDAO<CustomerModel,CustomerQueryModel> {
	public CustomerModel getByCustomerId(String customerId);
}
