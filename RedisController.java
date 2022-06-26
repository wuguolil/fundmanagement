package com.gloria.controller;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gloria.RedisUtils;
import com.zjy.redis.bean.ResultBO;
import com.zjy.redis.bean.User;
import com.zjy.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api(value = "redis缓存", tags = {"redis缓存"})
@Slf4j
@Validated
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 添加redis
     * @return
     */
    @PostMapping("/add")
    public ResultBO add(){

        ResultBO resultBO = new ResultBO();
        User user = new User();
        user.setUserName("用户名");
        user.setPassword("密码");
        user.setCode("验证码");
        String time = String.valueOf(System.currentTimeMillis());
        // 设置过期时间为2分钟
        redisTemplate.opsForValue().set(time, user, 120, TimeUnit.SECONDS);
        log.info("成功将 user: {}放入到redis中! key为: {}", user, time);

        resultBO.setCode(200);
        resultBO.setContent(user);
        resultBO.setMsg("加入redis成功!");

        return resultBO;
    }

    /**
     * 查询
     * @return
     */
    @GetMapping("/detail")
    public ResultBO detail(@RequestParam(value = "key", required=true)  String key) throws Exception{

        // 这里获取会得到一个map集合
        Map map = (Map)redisUtils.get(key);
        ObjectMapper json = new ObjectMapper();
        String params = json.writeValueAsString(map);

        User user = jsonToObject(params);
        log.info("从redis中获取到用户: {}", user);

        return ResultBO.success(user);
    }

    /**
     * 删除
     * @return
     */
    @GetMapping("/delete")
    public ResultBO delete(@RequestParam(value = "key", required=true)  String key) throws Exception{

        redisUtils.del(key);
        log.info("从redis中删除key为: {} 的用户信息", key);

        return ResultBO.success("从redis中删除用户成功!");
    }

    /**
     * 将json转换为对象
     * @param json
     * @return
     */
    private User jsonToObject(String json){

        User user = JSON.parseObject(json, User.class);
        return user;
    }
}


