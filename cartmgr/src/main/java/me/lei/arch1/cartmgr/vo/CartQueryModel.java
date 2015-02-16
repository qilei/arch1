package me.lei.arch1.cartmgr.vo;

import me.lei.pagination.dto.datatables.BasePageCriteria;

public class CartQueryModel extends BasePageCriteria {
    private Integer uuid;
    private Integer customerUuid;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getCustomerUuid() {
        return customerUuid;
    }

    public void setCustomerUuid(Integer customerUuid) {
        this.customerUuid = customerUuid;
    }

    public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}
