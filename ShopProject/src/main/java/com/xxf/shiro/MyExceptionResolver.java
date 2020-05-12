package com.xxf.shiro;
import com.xxf.Utils.JsonUtils;
import com.xxf.Utils.ResultUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class MyExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {

        // 判断是否ajax请求
        if ((request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            // 如果是ajax请求，JSON格式返回
            try {
                ResultUtil resultVo = new ResultUtil();
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
                if (e instanceof UnauthorizedException) {
                    resultVo.setCode(501);
                    resultVo.setMsg("权限不足。");
                } else if(e instanceof AuthorizationException) {
                    resultVo.setCode(501);
                    resultVo.setMsg("权限不足。");
                }else{
                    resultVo.setCode(500);
                    resultVo.setMsg("系统异常，请联系管理员。");
                }
                writer.write(JsonUtils.toJSon(resultVo).toString());
                writer.flush();
                writer.close();
            } catch (IOException eX) {
                eX.printStackTrace();
            }
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("extra_404_error");
        return view;
    }
}
