package com.example.aop;

import com.example.authority.Authority;
import com.example.authority.AuthorityUtils;
import com.example.utils.JwtUtils;
import com.example.utils.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限校验AOP
 */
@Aspect
@Component
@Order(1)
public class AuthorityAop {
    @Resource
    private HttpServletRequest request;

    //环绕通知
    @Around("@annotation(authority)")
    public Object authority(ProceedingJoinPoint joinPoint, Authority authority) throws Throwable {

        //获取用户id
        String uId = JwtUtils.getMemberIdByJwtToken(request);

        //根据权限类，来校验
        for (String s : authority.value()) {
            if(!AuthorityUtils.verify(uId, s)) {
                // 不具备该权限
                return R.error().code(403).message("权限不足");
            }
        }
        //执行原方法
        Object o = joinPoint.proceed();
        return o;
    }

}
