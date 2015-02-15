package me.lei.arch1.goodsmgr.service;


import me.lei.arch1.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.lei.arch1.goodsmgr.dao.GoodsDAO;
import me.lei.arch1.goodsmgr.vo.GoodsModel;
import me.lei.arch1.goodsmgr.vo.GoodsQueryModel;

@Service
@Transactional
public class GoodsService extends BaseService<GoodsModel,GoodsQueryModel> implements IGoodsService{
	private GoodsDAO dao = null;
	@Autowired
	private void setDao(GoodsDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
}