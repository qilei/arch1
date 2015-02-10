package me.lei.arch1.common.dao;

import me.lei.pagination.dto.PageMyBatis;

import java.util.List;


public interface BaseDAO<M,QM>{
	
	public void create(M m);
	public void update(M m);
	public void delete(int uuid);
	
	public M getByUuid(int uuid);
	public PageMyBatis<M> getByPage(QM qm);
}
