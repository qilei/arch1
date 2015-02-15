package me.lei.arch1.cartmgr.service;


import me.lei.arch1.cartmgr.dao.CartDAO;
import me.lei.arch1.cartmgr.vo.CartModel;
import me.lei.arch1.cartmgr.vo.CartQueryModel;
import me.lei.arch1.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService extends BaseService<CartModel,CartQueryModel> implements ICartService{
	private CartDAO dao = null;
	@Autowired
	private void setDao(CartDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	
}