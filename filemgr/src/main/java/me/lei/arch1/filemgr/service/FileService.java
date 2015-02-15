package me.lei.arch1.filemgr.service;


import me.lei.arch1.common.service.BaseService;
import me.lei.arch1.filemgr.dao.FileDAO;
import me.lei.arch1.filemgr.vo.FileModel;
import me.lei.arch1.filemgr.vo.FileQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FileService extends BaseService<FileModel,FileQueryModel> implements IFileService{
	private FileDAO dao = null;
	@Autowired
	private void setDao(FileDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	public FileModel getByFileName(String fileName) {
		FileQueryModel qm = new FileQueryModel();
		qm.setFileName(fileName);
		
		List<FileModel> list = dao.getByPage(qm);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}
	
}