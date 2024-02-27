package com.rfgvieira.Car_Fipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class CastData implements ICastData {

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T castData(String json, Class<T> type) {
        try{
            return mapper.readValue(json, type);
        }  catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getList(String json, Class<T> type) {
        CollectionType list = mapper.getTypeFactory().constructCollectionType(List.class, type);
        try {
            return mapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
