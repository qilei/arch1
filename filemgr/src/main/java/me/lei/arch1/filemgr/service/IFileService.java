package me.lei.arch1.filemgr.service;


import me.lei.arch1.common.service.IBaseService;
import me.lei.arch1.filemgr.vo.FileModel;
import me.lei.arch1.filemgr.vo.FileQueryModel;

public interface IFileService extends IBaseService<FileModel,FileQueryModel>{
	public FileModel getByFileName(String fileName);
}

