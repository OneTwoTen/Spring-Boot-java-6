package com.Java6.Util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectMapperUtils {
    @Autowired
    private ModelMapper mapper;

    private ObjectMapperUtils(){

    }

    public <C,E> C convertEntityAndDto(final E entity, Class<C> outputClass){
        return mapper.map(entity, outputClass);
    }

    public <C,E> List<C> mapAll(final Collection<? extends E> input, final Class<C> outputClass){
        return input.stream().map(
            entity -> convertEntityAndDto(entity, outputClass)
        ).collect(Collectors.toList());
    }
}
