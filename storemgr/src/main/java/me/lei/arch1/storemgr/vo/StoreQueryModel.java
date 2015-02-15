package me.lei.arch1.storemgr.vo;

import me.lei.pagination.dto.datatables.BasePageCriteria;

public class StoreQueryModel extends BasePageCriteria {
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
