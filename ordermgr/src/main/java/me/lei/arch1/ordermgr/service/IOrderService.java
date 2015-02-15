package me.lei.arch1.ordermgr.service;


import me.lei.arch1.common.service.IBaseService;
import me.lei.arch1.ordermgr.vo.OrderModel;
import me.lei.arch1.ordermgr.vo.OrderQueryModel;

public interface IOrderService extends IBaseService<OrderModel,OrderQueryModel>{
	public void order(int customerUuid);
}

