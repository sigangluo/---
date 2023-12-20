package cn.edu.scau.cmi.luosigang.everyshare.handler;

import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseResult;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import cn.edu.scau.cmi.luosigang.everyshare.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResponseResult<Object> rr = null;
        if (authentication != null) {
            rr = new ResponseResult<>(ResponseStatus.SUCCESS);
        } else {
            rr = new ResponseResult<>(ResponseStatus.USER_NOT_LOGIN);
        }
        ResponseUtil.out(response,rr);
    }
}