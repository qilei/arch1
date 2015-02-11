package me.lei.arch1.customermgr.service;


import me.lei.arch1.common.service.IBaseService;
import me.lei.arch1.customermgr.vo.CustomerModel;
import me.lei.arch1.customermgr.vo.CustomerQueryModel;

public interface ICustomerService extends IBaseService<CustomerModel, CustomerQueryModel> {
	public CustomerModel getByCustomerId(String customerId);
}
