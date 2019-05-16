package com.shengrong.chemicalsystem.model.entity.commom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageEntity {
    private int pageSize;
    private int pageNumber;
}
