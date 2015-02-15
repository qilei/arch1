package me.lei.arch1.storemgr.dao;

import me.lei.arch1.common.dao.BaseDAO;
import me.lei.arch1.storemgr.vo.StoreModel;
import me.lei.arch1.storemgr.vo.StoreQueryModel;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreDAO extends BaseDAO<StoreModel,StoreQueryModel> {
	public StoreModel getByGoodsUuid(int goodsUuid);
}
