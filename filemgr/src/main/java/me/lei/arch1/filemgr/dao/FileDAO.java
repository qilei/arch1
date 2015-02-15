package me.lei.arch1.filemgr.dao;

import me.lei.arch1.common.dao.BaseDAO;
import me.lei.arch1.filemgr.vo.FileModel;
import me.lei.arch1.filemgr.vo.FileQueryModel;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDAO extends BaseDAO<FileModel,FileQueryModel>{
}
