package me.lei.arch1.ordermgr.service;


import me.lei.arch1.cartmgr.service.ICartService;
import me.lei.arch1.cartmgr.vo.CartModel;
import me.lei.arch1.cartmgr.vo.CartQueryModel;
import me.lei.arch1.common.service.BaseService;
import me.lei.arch1.ordermgr.dao.OrderDAO;
import me.lei.arch1.ordermgr.vo.OrderDetailModel;
import me.lei.arch1.ordermgr.vo.OrderModel;
import me.lei.arch1.ordermgr.vo.OrderQueryModel;
import me.lei.arch1.storemgr.service.IStoreService;
import me.lei.arch1.storemgr.vo.StoreModel;
import me.lei.pagination.dto.PageMyBatis;
import me.lei.pagination.dto.datatables.PagingCriteria;
import me.lei.util.format.DateFormatHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService extends BaseService<OrderModel,OrderQueryModel> implements IOrderService{
	private OrderDAO dao = null;
	@Autowired
	private ICartService ics = null;
	@Autowired
	private IOrderDetailService iods = null;
	@Autowired
	private IStoreService iss = null;
	@Autowired
	private void setDao(OrderDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	public void order(int customerUuid) {

			CartQueryModel cqm = new CartQueryModel();
        cqm.setPage(PagingCriteria.getDefaultCriteria());
			cqm.setCustomerUuid(customerUuid);

			PageMyBatis<CartModel> page = ics.getByPage(cqm);
			//2:
			float totalMoney = 0.0f;
			for(CartModel cm : page){
				totalMoney += 10;
			}

			OrderModel order = new OrderModel();
			order.setCustomerUuid(customerUuid);
			order.setOrderTime(DateFormatHelper.long2str(System.currentTimeMillis()));
			order.setSaveMoney(0.0F);
			order.setTotalMoney(totalMoney);
			order.setState(1);

			create(order);

			OrderQueryModel oqm = new OrderQueryModel();
			oqm.setOrderTime(order.getOrderTime());

        PageMyBatis<OrderModel>  orderPage = getByPage(oqm);
			order = orderPage.get(0);

			//3:
			for(CartModel cm : page){
				OrderDetailModel odm = new OrderDetailModel();
				odm.setGoodsUuid(cm.getGoodsUuid());
				odm.setOrderNum(cm.getBuyNum());
				odm.setPrice(10.0f);
				odm.setMoney(odm.getPrice() * odm.getOrderNum());
				odm.setSaveMoney(0.0f);
				odm.setOrderUuid(order.getUuid());

				iods.create(odm);
				//4:
				StoreModel storeModel = iss.getByGoodsUuid(cm.getGoodsUuid());
				storeModel.setStoreNum(storeModel.getStoreNum() - odm.getOrderNum());
				iss.update(storeModel);

				//5:
				ics.delete(cm.getUuid());
			}

	}
	
}