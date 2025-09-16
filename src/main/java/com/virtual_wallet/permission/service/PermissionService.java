package com.virtual_wallet.permission.service;

import com.virtual_wallet.permission.dto.PermissionRequest;
import com.virtual_wallet.permission.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getAllPermissions();
    Permission getPermissionById(Long permissionId);
    Permission createPermission(PermissionRequest permissionRequest);
    Permission updatePermission(Long permissionId, PermissionRequest permissionRequest);
    String deletePermissionById(Long permissionId);
}
