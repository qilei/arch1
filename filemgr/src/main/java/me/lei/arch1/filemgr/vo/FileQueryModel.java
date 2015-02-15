package me.lei.arch1.filemgr.vo;

import me.lei.pagination.dto.datatables.BasePageCriteria;

public class FileQueryModel extends BasePageCriteria {
    private Integer uuid;
    private String fileName;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }
    public void setFileName(String obj){
        this.fileName = obj;
    }
    public String getFileName(){
        return this.fileName;
    }

	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}
