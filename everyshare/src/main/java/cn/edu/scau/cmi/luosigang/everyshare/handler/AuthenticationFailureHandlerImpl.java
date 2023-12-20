package cn.edu.scau.cmi.luosigang.everyshare.handler;

import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseResult;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import cn.edu.scau.cmi.luosigang.everyshare.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseResult<Object> rr = null;
        if (e instanceof UsernameNotFoundException){
            rr = new ResponseResult<>(ResponseStatus.USERNAME_NOT_FOUNT);
        }else if (e instanceof BadCredentialsException) {
            rr = new ResponseResult<>(ResponseStatus.USER_PASSWORD_ERROR);
        } else if (e instanceof DisabledException) {
            rr = new ResponseResult<>(ResponseStatus.USER_ACCOUNT_LOGOFF);
        } else if (e instanceof LockedException) {
            rr = new ResponseResult<>(ResponseStatus.USER_ACCOUNT_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            rr = new ResponseResult<>(ResponseStatus.USER_ACCOUNT_NOT_EXIST);
        }else{
            rr = new ResponseResult<>(ResponseStatus.COMMON_ERROR);
        }
        ResponseUtil.out(response,rr);
    }
}