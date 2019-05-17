package com.shengrong.chemicalsystem.controller.response.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class PageResultResponse<T> {
    private long total;
    private List<T> data;
}
