package com.virtual_wallet.permission.exception;

public class PermissionAlreadyExistsException extends RuntimeException{

    public PermissionAlreadyExistsException(String message) {
        super(message);
    }
}
