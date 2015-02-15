package me.lei.arch1.storemgr.service;


import me.lei.arch1.common.service.BaseService;
import me.lei.arch1.storemgr.dao.StoreDAO;
import me.lei.arch1.storemgr.vo.StoreModel;
import me.lei.arch1.storemgr.vo.StoreQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StoreService extends BaseService<StoreModel, StoreQueryModel> implements IStoreService {
    private StoreDAO dao = null;

    @Autowired
    private void setDao(StoreDAO dao) {
        this.dao = dao;
        super.setDAO(dao);
    }

    public StoreModel getByGoodsUuid(int goodsUuid) {
        return dao.getByGoodsUuid(goodsUuid);
    }

}