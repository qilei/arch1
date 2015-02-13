package me.lei.pagination.dto.datatables;

import java.io.Serializable;

/**
 * Created by qilei on 2015/2/2.
 */
public class BasePageCriteria implements Serializable {
    private PagingCriteria page;

    public BasePageCriteria() {
        this.page = PagingCriteria.getDefaultCriteria();
    }

    public BasePageCriteria(PagingCriteria page) {
        this.page = page;
    }

    public PagingCriteria getPage() {
        return page;
    }

    public void setPage(PagingCriteria page) {
        this.page = page;
    }
}
