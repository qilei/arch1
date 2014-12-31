package me.lei.arch1.common.service;


import me.lei.arch1.common.vo.BaseModel;
import me.lei.pageutil.Page;

public interface IBaseService<M,QM extends BaseModel> {
	public void create(M m);
	public void update(M m);
	public void delete(int uuid);
	
	public M getByUuid(int uuid);
	public Page<M> getByConditionPage(QM qm);
}
