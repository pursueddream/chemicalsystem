package com.shengrong.chemicalsystem.controller.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
public class PageRequest {

    @NonNull
    @Min(value = 1)
    @Max(value = 100)
    private int pageSize;

    @NonNull
    @Min(value = 1)
    private int pageNumber;
}
