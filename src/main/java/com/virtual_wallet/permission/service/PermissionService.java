package com.virtual_wallet.permission.service;

import com.virtual_wallet.permission.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getAllPermissions();
    Permission getPermissionById(Long permissionId);
    Permission createPermission(Permission permission);
    Permission updatePermission(Permission permission);
    String deletePermissionById(Long permissionId);
}
