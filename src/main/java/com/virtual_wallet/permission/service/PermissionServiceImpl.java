package com.virtual_wallet.permission.service;

import com.virtual_wallet.permission.dto.PermissionRequest;
import com.virtual_wallet.permission.entity.Permission;
import com.virtual_wallet.permission.exception.PermissionAlreadyExistsException;
import com.virtual_wallet.permission.exception.PermissionNotFoundException;
import com.virtual_wallet.permission.repository.PermissionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService{


    private final PermissionRepository permissionRepository;


    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermissionById(Long permissionId) {
        return this.getPermission(permissionId);
    }

    @Override
    public Permission createPermission(PermissionRequest permissionRequest) {

        if (permissionRepository.existsByName(permissionRequest.permissionName())) {
            throw new PermissionAlreadyExistsException("Permission with name " + permissionRequest.permissionName() + " already exists");
        }

        Permission permission = new Permission();
        permission.setName(permissionRequest.permissionName());

        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Long permissionId, PermissionRequest permissionRequest) {
        Permission permission = this.getPermission(permissionId);
        permission.setName(permissionRequest.permissionName());
        return permissionRepository.save(permission);
    }

    @Override
    public String deletePermissionById(Long permissionId) {
        if (!permissionRepository.existsById(permissionId)) {
            throw new PermissionNotFoundException("Permission not found with ID: " + permissionId);
        }
        permissionRepository.deleteById(permissionId);
        return "Permission successfully deleted";
    }

    private Permission getPermission(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException("Permission not found with ID: " + permissionId));
    }
}
