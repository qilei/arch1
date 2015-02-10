package me.lei.arch1.common.service;


import me.lei.arch1.common.dao.BaseDAO;
import me.lei.pagination.dto.PageMyBatis;
import me.lei.pagination.dto.datatables.BasePageCriteria;



public class BaseService<M, QM extends BasePageCriteria> implements IBaseService<M,QM> {
	private BaseDAO dao = null;
	public void setDAO(BaseDAO dao){
		this.dao = dao;
	}
	public void create(M m) {
		dao.create(m);
	}

	public void update(M m) {
		dao.update(m);
	}

	public void delete(int uuid) {
		dao.delete(uuid);
	}

	public M getByUuid(int uuid) {
		return (M)dao.getByUuid(uuid);
	}

	public PageMyBatis<M> getByPage(QM qm) {
        PageMyBatis<M> list = dao.getByPage(qm);
        return list;
	}
}
