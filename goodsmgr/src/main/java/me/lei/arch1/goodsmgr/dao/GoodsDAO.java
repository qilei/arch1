package me.lei.arch1.goodsmgr.dao;

import me.lei.arch1.common.dao.BaseDAO;
import me.lei.arch1.goodsmgr.vo.GoodsModel;
import me.lei.arch1.goodsmgr.vo.GoodsQueryModel;
import org.springframework.stereotype.Repository;


@Repository
public interface GoodsDAO extends BaseDAO<GoodsModel,GoodsQueryModel> {
//	public Page<GoodsModel> getByCondition(GoodsQueryModel qm);
}
