package com.zpq.controller;

import com.zpq.model.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 35147
 */
@RestController
public class LoginController {

    Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @RequestMapping("/login")
    public Result<Boolean> login(String username,String password){
        boolean flag = ("admin".equals(username)) && ("123456".equals(password));
        if(flag){
            return new Result<>(true,200,"success",true);
        }else {
            return new Result<>(true,200,"error",false);
        }
    }

    @RequestMapping("/test")
    public String hello() {
        logger.info("hello");
        return "hello";
    }
}
