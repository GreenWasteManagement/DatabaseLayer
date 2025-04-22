package com.greenwaste.javadatabaseconnector.webhttp.authorization.jwtreader.annotation;

import com.greenwaste.javadatabaseconnector.webhttp.authorization.Authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthRole {
    Authorization.UserRolePermission role();
}

