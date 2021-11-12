package com.example.demo.controller;



import com.example.demo.base.ResponseBase;
import com.example.demo.entity.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author nan.gai
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 登陆成功返回token给前台，之后每调一个接口都需要校验token之后有效

     * @return
     */
    @PostMapping("/login")
    public ResponseBase UserLogin(@RequestBody @Validated UserDto userDto, HttpServletRequest request) {
        return userService.UserLogin(userDto.getUserName(), userDto.getPassword());
    }

    /**
     * 查看用户的基本信息
     * @param id
     * @return
     */
    @GetMapping("/getUserInfo")
    public ResponseBase getUserInfo(Integer id) {
        return userService.getUserInfo(id);
    }

    @Autowired
    private UserService userService;
}