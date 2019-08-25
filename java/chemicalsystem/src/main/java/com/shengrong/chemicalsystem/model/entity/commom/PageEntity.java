package com.shengrong.chemicalsystem.model.entity.commom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageEntity {
    private int pageNumber;
    private int pageSize;

    public PageEntity() {
    }

    public PageEntity(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
