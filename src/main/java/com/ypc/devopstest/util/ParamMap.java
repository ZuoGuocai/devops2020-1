package com.ypc.devopstest.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ParamMap extends HashMap {

    public static ParamMap init(HttpServletRequest httpServletRequest){
        ParamMap paramMap  = new ParamMap();
        httpServletRequest.getParameterMap().forEach((k,v)->{
            paramMap.put(k,v[0]);
        });
        return paramMap;
    }

    public static <T> Object toObject (ParamMap paramMap, Class<?> beanClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(paramMap, beanClass);
    }

    //page 第几页,从0开始
    //limit 每页多少条
    public void initPage(){
        Integer limit = 10;
        Integer page = 0;
        Object pageSizeObj = this.get("pageSize");
        Object pageIndexObj = this.get("pageIndex");
        if(pageSizeObj != null && pageIndexObj != null){
            page = Integer.valueOf(String.valueOf(pageIndexObj));
            limit = Integer.valueOf(String.valueOf(pageSizeObj));
        }
        this.put("limit",limit);
        this.put("page",(limit==0?++limit:limit)*page);
    }

    public Long getLong(String key){
        Object value = this.get(key);
        if(value == null){
            return null;
        }
        return Long.valueOf(value.toString());
    }

    public String getString(String key){
        Object value = this.get(key);
        if(value == null){
            return null;
        }
        return value.toString();
    }

    public Integer getInt(String key){
        Object value = this.get(key);
        if(value == null){
            return null;
        }
        return Integer.valueOf(value.toString());
    }

}
