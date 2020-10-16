package com.ypc.devopstest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.ypc.devopstest.util.Result;
import com.ypc.devopstest.util.ParamMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  fubiao
 * @email fubiao@gongchangtemai.com.cn
 * @time 15/10/2020
 */
@Slf4j
@Service
public class UserService {


    public Result listBy(ParamMap queryMap) {
        Result result = new Result<>();

        Map listData = new HashMap();
        listData.put("list",1);
        listData.put("totalCount",2);

        result.setCode(200);
        result.setData(listData);
        return result;
    }

}
