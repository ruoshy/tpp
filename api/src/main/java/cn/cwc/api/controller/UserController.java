package cn.cwc.api.controller;

import cn.cwc.api.entity.User;
import cn.cwc.api.mapper.UserMapper;
import cn.cwc.api.model.Action;
import cn.cwc.api.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/login")
    public Result<User> login(User user) {
        user = userMapper.login(user);
        if (user != null) {
            String token = UUID.randomUUID().toString().replace("-", "");
            user.setToken(token);
            userMapper.updateToken(user);
        }
        return new Result<>(user, user == null ? Action.FAIL : Action.OK);
    }
}
