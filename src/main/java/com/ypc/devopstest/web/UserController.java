package com.ypc.devopstest.web;

import com.ypc.devopstest.domain.User;
import com.ypc.devopstest.service.UserService;
import com.ypc.devopstest.util.Result;
import com.ypc.devopstest.util.ParamMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author  fubiao
 * @email fubiao@gongchangtemai.com.cn
 * @time 15/10/2020
 */
@Slf4j
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    private Integer count = 0;

    @PostMapping("/1.0.0/user/get")
    public Result get(HttpServletRequest httpServletRequest) throws ExecutionException {
        ParamMap paramMap = ParamMap.init(httpServletRequest);
        this.count++;
        //if(this.count == 2){
            userService = null;
        //}
        return Result.ok(userService.listBy(paramMap));
    }


}
