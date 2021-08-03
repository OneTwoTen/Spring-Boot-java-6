package com.Java6.dto;

import org.springframework.data.domain.Sort;

public class PageDto {
    private int pageIndex=0;
    private int pageSize=15;
    private Sort.Direction direction;
    private String sortBy;
}
