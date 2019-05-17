package com.shengrong.chemicalsystem.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shengrong.chemicalsystem.controller.response.common.PageResultResponse;
import com.shengrong.chemicalsystem.model.entity.commom.BaseEntity;
import com.shengrong.chemicalsystem.model.entity.commom.PageEntity;
import com.shengrong.chemicalsystem.service.BaseService;
import com.shengrong.chemicalsystem.utils.DateUtils;
import com.shengrong.chemicalsystem.utils.IdUtils;

import java.util.List;

public abstract class AbstractBaseService<T extends BaseEntity> implements BaseService<T> {

    private BaseMapper<T> dao;

    public AbstractBaseService(BaseMapper<T> dao) {
        this.dao = dao;
    }

    @Override
    public PageResultResponse<T> queryPage(T t, PageEntity pageEntity) {
        Wrapper<T> wrapper = new EntityWrapper<>(t);
        Page<T> page = new Page<>(pageEntity.getPageNumber(), pageEntity.getPageSize());
        List<T> list = dao.selectPage(page, wrapper);
        int total = dao.selectCount(wrapper);
        PageResultResponse<T> result = new PageResultResponse<>();
        result.setData(list);
        result.setTotal(total);
        return result;
    }

    @Override
    public T queryById(String id) {
        return dao.selectById(id);
    }

    @Override
    public String insert(T t) {
        String uuid = IdUtils.getUUID();
        //设置id
        t.setId(uuid);
        //创建时间修改
        t.setCreateTime(DateUtils.getCurrentTime());
        t.setModifyTime(DateUtils.getCurrentTime());
        dao.insert(t);
        return uuid;
    }

    @Override
    public void updateById(T t) {
        //更新时间
        t.setModifyTime(DateUtils.getCurrentTime());
        dao.updateById(t);
    }

    @Override
    public void deleteById(String id) {
        dao.deleteById(id);
    }
}
