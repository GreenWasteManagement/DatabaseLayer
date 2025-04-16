package com.greenwaste.javadatabaseconnector.webhttp.authorization.aspect;

import com.greenwaste.javadatabaseconnector.webhttp.authorization.Authorization.UserRolePermission;
import com.greenwaste.javadatabaseconnector.webhttp.authorization.annotation.AuthRole;
import com.greenwaste.javadatabaseconnector.webhttp.authorization.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class AuthRoleAspect {

    @Before("@annotation(com.greenwaste.javadatabaseconnector.webhttp.authorization.annotation.AuthRole)")
    public void checkRolePermission(JoinPoint joinPoint) throws UnauthorizedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AuthRole authRole = method.getAnnotation(AuthRole.class);

        HttpServletRequest request = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        String userRole = (String) request.getAttribute("role");

        if (userRole == null ||
                !Objects.requireNonNull(UserRolePermission.fromString(userRole))
                        .canAccess(authRole.role())) {
            throw new UnauthorizedException("You do not have permission to access this resource");
        }
    }
}
