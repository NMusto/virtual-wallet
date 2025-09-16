package com.virtual_wallet.permission.exception;

public class PermissionNotFoundException extends RuntimeException{

    public PermissionNotFoundException(String message) {
        super(message);
    }
}
