package me.lei.arch1.common.service;


import me.lei.arch1.common.vo.BaseModel;
import me.lei.pagination.dto.PageMyBatis;
import me.lei.pagination.dto.datatables.BasePageCriteria;

public interface IBaseService<M,QM extends BasePageCriteria> {
	public void create(M m);
	public void update(M m);
	public void delete(int uuid);
	
	public M getByUuid(int uuid);
	public PageMyBatis<M> getByPage(QM qm);
}
