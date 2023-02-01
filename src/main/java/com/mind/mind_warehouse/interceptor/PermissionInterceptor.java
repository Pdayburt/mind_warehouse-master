package com.mind.mind_warehouse.interceptor;

import com.mind.mind_warehouse.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        String servletPath = request.getServletPath();
        System.out.println("客户端请求的路由是"+servletPath);
        String token = request.getHeader("token");
        if(token == null){
            response.getOutputStream().print(0);
            return false;
        }else {
            try {
                JwtUtil.parseToken(token);
                return true;
            } catch (ExpiredJwtException e) {
                response.getOutputStream().print(1);
            }catch (SignatureException e1){
                response.getOutputStream().print(2);
            }catch (RuntimeException e2){
                response.getOutputStream().print(3);
            }
        }
        return false;
    }
}
