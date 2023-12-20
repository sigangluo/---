package cn.edu.scau.cmi.luosigang.everyshare.util;

import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
    public static void out(HttpServletResponse response, ResponseResult<?> rr) throws IOException {
        response.setContentType("text/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), rr);
    }
}
