package me.lei.arch1.ordermgr.service;


import me.lei.arch1.common.service.BaseService;
import me.lei.arch1.ordermgr.dao.OrderDetailDAO;
import me.lei.arch1.ordermgr.vo.OrderDetailModel;
import me.lei.arch1.ordermgr.vo.OrderDetailQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderDetailService extends BaseService<OrderDetailModel,OrderDetailQueryModel> implements IOrderDetailService{
	private OrderDetailDAO dao = null;
	@Autowired
	private void setDao(OrderDetailDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	
}