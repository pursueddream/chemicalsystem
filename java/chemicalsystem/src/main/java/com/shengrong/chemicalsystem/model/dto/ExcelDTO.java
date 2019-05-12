package com.shengrong.chemicalsystem.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Setter
@Getter
@ToString
public class ExcelDTO implements Serializable {
    //页签名称
    private String tagName;
    //标题
    private List<String> titles;
    //数据
    private List<List<String>> rows;
}
