package me.lei.arch1.ordermgr.dao;

import me.lei.arch1.common.dao.BaseDAO;
import me.lei.arch1.ordermgr.vo.OrderDetailModel;
import me.lei.arch1.ordermgr.vo.OrderDetailQueryModel;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDAO extends BaseDAO<OrderDetailModel,OrderDetailQueryModel>{
	
}
