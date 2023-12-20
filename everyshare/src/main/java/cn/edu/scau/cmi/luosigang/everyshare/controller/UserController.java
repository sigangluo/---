package cn.edu.scau.cmi.luosigang.everyshare.controller;


import cn.edu.scau.cmi.luosigang.everyshare.entity.User;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseResult;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import cn.edu.scau.cmi.luosigang.everyshare.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping("/register/{role}")//注册
    public ResponseEntity<?> handleRegister(String username,String password,@PathVariable String role) {
        //TODO 注册数据合法性检查
        return ResponseEntity.ok(new ResponseResult<>(userService.register(username,password,role)));
    }
    @GetMapping("/logoff")//注销
    public ResponseEntity<?> handleFollow(HttpServletRequest request) throws ServletException {
        int id = userService.getLoginUser().getId();
        request.logout();
        return ResponseEntity.ok(new ResponseResult<>(userService.logoff(id)));
    }
    @GetMapping("/current")//当前用户
    public ResponseEntity<?> handleCurrentUser(){
        User user = userService.getLoginUser();
        Map<String,Object> data = null;
        if (user != null){
            data = new HashMap<>();
            data.put("name",user.getUsername());
            data.put("role",user.getRole().split("_")[1].toLowerCase());
        }
        return ResponseEntity.ok(new ResponseResult<>(ResponseStatus.SUCCESS,data));
    }
}
