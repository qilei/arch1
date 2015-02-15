package me.lei.arch1.storemgr.service;


import me.lei.arch1.common.service.IBaseService;
import me.lei.arch1.storemgr.vo.StoreModel;
import me.lei.arch1.storemgr.vo.StoreQueryModel;

public interface IStoreService extends IBaseService<StoreModel, StoreQueryModel> {
    public StoreModel getByGoodsUuid(int goodsUuid);
}

