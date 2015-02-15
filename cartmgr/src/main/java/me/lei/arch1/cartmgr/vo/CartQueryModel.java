package me.lei.arch1.cartmgr.vo;

import me.lei.pagination.dto.datatables.BasePageCriteria;

public class CartQueryModel extends BasePageCriteria {
    private Integer uuid;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}
