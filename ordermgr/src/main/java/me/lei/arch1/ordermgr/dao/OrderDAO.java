package me.lei.arch1.ordermgr.dao;

import me.lei.arch1.common.dao.BaseDAO;
import me.lei.arch1.ordermgr.vo.OrderModel;
import me.lei.arch1.ordermgr.vo.OrderQueryModel;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends BaseDAO<OrderModel,OrderQueryModel>{
	
}
