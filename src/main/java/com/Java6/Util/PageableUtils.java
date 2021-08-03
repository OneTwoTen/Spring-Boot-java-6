package com.Java6.Util;

import com.Java6.dto.PageDto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageableUtils {
    public static Pageable pageableUtils(String sortDirection, String sortBy, int pageIndex, int pageSize) {
        Direction direction = null;
        // if(sortBy == null && sortDirection == null){
        //     direction = Sort.Direction.ASC;
        //     sortBy = "id";
        // }
        // if(sortDirection.equals("ASC")){
        //     direction = Sort.Direction.ASC;
        // }
        direction = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
        return  pageable;
    }
}
