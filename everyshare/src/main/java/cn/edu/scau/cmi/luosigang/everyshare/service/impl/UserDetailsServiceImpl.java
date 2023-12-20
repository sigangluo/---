package cn.edu.scau.cmi.luosigang.everyshare.service.impl;

import cn.edu.scau.cmi.luosigang.everyshare.entity.User;
import cn.edu.scau.cmi.luosigang.everyshare.entity.UserLogin;
import cn.edu.scau.cmi.luosigang.everyshare.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = userMapper.selectOne((new LambdaQueryWrapper<User>()).eq(User::getUsername,username));
        if (user == null) throw new UsernameNotFoundException("用户名不存在");
        return new UserLogin(user);
    }
}
