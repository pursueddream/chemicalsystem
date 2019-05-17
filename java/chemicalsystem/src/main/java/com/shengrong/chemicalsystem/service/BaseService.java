package com.shengrong.chemicalsystem.service;

import com.shengrong.chemicalsystem.controller.response.common.PageResultResponse;
import com.shengrong.chemicalsystem.model.entity.commom.BaseEntity;
import com.shengrong.chemicalsystem.model.entity.commom.PageEntity;

public interface BaseService<T extends BaseEntity> {

    /**
     * 查询全部
     * @param t t
     * @param pageEntity pageEntity
     * @return return
     */
    PageResultResponse<T> queryPage(T t, PageEntity pageEntity);

    /**
     * 查询单个
     * @param id id
     * @return return
     */
    T queryById(String id);

    /**
     * 添加
     * @param t t
     * @return 主键
     */
    String insert(T t);

    /**
     * 更新t
     * @param t t
     */
    void updateById(T t);

    /**
     * 删除
     * @param id id
     */
    void deleteById(String id);
}
