package me.lei.arch1.customermgr.vo;


import me.lei.pagination.dto.datatables.BasePageCriteria;
import me.lei.pagination.dto.datatables.PagingCriteria;

public class CustomerQueryModel extends BasePageCriteria {
    private Integer uuid;
    private String customerId;
    private String showName;

    public CustomerQueryModel() {
        super();
    }

    public CustomerQueryModel(PagingCriteria page) {
        super(page);
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
