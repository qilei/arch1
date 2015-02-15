package me.lei.arch1.goodsmgr.vo;

import me.lei.pagination.dto.datatables.BasePageCriteria;

public class GoodsQueryModel extends BasePageCriteria {
    private Integer uuid;
    private String name;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}
