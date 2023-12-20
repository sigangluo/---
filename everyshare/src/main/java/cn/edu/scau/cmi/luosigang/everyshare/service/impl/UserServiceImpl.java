package cn.edu.scau.cmi.luosigang.everyshare.service.impl;

import cn.edu.scau.cmi.luosigang.everyshare.entity.User;
import cn.edu.scau.cmi.luosigang.everyshare.entity.UserLogin;
import cn.edu.scau.cmi.luosigang.everyshare.mapper.UserMapper;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import cn.edu.scau.cmi.luosigang.everyshare.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseStatus register(String username, String password, String role) {
        System.out.println(username);
        System.out.println(password);
        if (!userMapper.exists((new LambdaQueryWrapper<User>()).eq(User::getUsername,username))){
            String encodePassword = passwordEncoder.encode(password);
            User user = new User(username,encodePassword,"ROLE_"+role.toUpperCase());
            if (userMapper.insert(user) > 0)
                return ResponseStatus.USER_REGISTRATION_SUCCESS;
            else return ResponseStatus.USER_REGISTRATION_ERROR;
        }
        else return ResponseStatus.USERNAME_EXIST;
    }
    @Override
    public ResponseStatus logoff(int id) {
        if (userMapper.deleteById(id) > 0)
            return ResponseStatus.USER_LOGOFF_SUCCESS;
        else return ResponseStatus.USER_LOGOFF_ERROR;
    }

    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName().equals("anonymousUser")) return null;
        else {
            UserLogin user = (UserLogin) authentication.getPrincipal();
            return user.getUser();
        }
    }
}
