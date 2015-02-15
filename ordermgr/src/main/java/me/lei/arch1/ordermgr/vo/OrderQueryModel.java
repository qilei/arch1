package me.lei.arch1.ordermgr.vo;

import me.lei.pagination.dto.datatables.BasePageCriteria;

public class OrderQueryModel extends BasePageCriteria {
    private Integer uuid;
    private String orderTime;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}
