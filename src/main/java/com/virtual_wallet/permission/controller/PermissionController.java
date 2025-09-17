package com.virtual_wallet.permission.controller;

import com.virtual_wallet.permission.dto.PermissionRequest;
import com.virtual_wallet.permission.entity.Permission;
import com.virtual_wallet.permission.service.PermissionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@Validated
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.getAllPermissions();
        return ResponseEntity.status(HttpStatus.OK).body(permissions);
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable @Positive @NotNull Long permissionId) {
        Permission permission = permissionService.getPermissionById(permissionId);
        return ResponseEntity.status(HttpStatus.OK).body(permission);
    }

    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody @Valid PermissionRequest permissionRequest) {
        Permission permission = permissionService.createPermission(permissionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(permission);
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<Permission> updatePermission(@PathVariable @Positive @NotNull Long permissionId,
                                                       @RequestBody @Valid PermissionRequest permissionRequest) {
        Permission permission = permissionService.updatePermission(permissionId, permissionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(permission);
    }

    @DeleteMapping("/permissionId")
    public ResponseEntity<String> deletePermission(@PathVariable @Positive @NotNull Long permissionId) {
        String response = permissionService.deletePermissionById(permissionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
