package com.greenwaste.javadatabaseconnector.webhttp.authorization.jwtreader.exceptions;


public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
