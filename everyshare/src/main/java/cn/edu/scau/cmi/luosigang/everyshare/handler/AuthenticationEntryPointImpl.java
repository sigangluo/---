package cn.edu.scau.cmi.luosigang.everyshare.handler;

import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseResult;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import cn.edu.scau.cmi.luosigang.everyshare.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response,new ResponseResult<>(ResponseStatus.USER_NOT_LOGIN));
    }
}