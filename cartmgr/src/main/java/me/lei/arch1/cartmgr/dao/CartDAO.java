package me.lei.arch1.cartmgr.dao;

import me.lei.arch1.cartmgr.vo.CartModel;
import me.lei.arch1.cartmgr.vo.CartQueryModel;
import me.lei.arch1.common.dao.BaseDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDAO extends BaseDAO<CartModel,CartQueryModel>{
	
}
