package com.shengrong.chemicalsystem.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class PageResult<T> {
    private long total;
    private List<T> data;
}
