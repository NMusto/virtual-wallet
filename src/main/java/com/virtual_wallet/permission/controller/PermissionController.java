package com.virtual_wallet.permission.controller;

import com.virtual_wallet.permission.dto.PermissionRequest;
import com.virtual_wallet.permission.entity.Permission;
import com.virtual_wallet.permission.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@Validated
@RequiredArgsConstructor
@Tag(name = "Permissions", description = "Endpoints related to permissions management")
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping
    @Operation(
            summary = "Get all Permissions",
            description = "Retrieves a list of all permissions available in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Permissions retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permission.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No permissions fond",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
    })
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.getAllPermissions();
        return ResponseEntity.status(HttpStatus.OK).body(permissions);
    }

    @GetMapping("/{permissionId}")
    @Operation(summary = "Get permission by ID",
            description = "Retrieve a specific permission by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Permission retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permission.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid permission ID",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "permission not fond",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
    })
    public ResponseEntity<Permission> getPermissionById(
            @Parameter(description = "Unique identifier of the permission")
            @PathVariable @Positive @NotNull Long permissionId) {
        Permission permission = permissionService.getPermissionById(permissionId);
        return ResponseEntity.status(HttpStatus.OK).body(permission);
    }

    @PostMapping
    @Operation(summary = "Create a new permission", description = "Add a new permission to the system")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Permission created successfully.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permission.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input or validation error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "A permission with the same name already exists.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<Permission> createPermission(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Permission data required to create a new permission"
            )
            @RequestBody @Valid PermissionRequest permissionRequest) {
        Permission permission = permissionService.createPermission(permissionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(permission);
    }

    @PutMapping("/{permissionId}")
    @Operation(summary = "Update a permission", description = "Update an existing permission by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Permission updated successfully.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permission.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input or validation error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Permission not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<Permission> updatePermission(
            @Parameter(description = "Unique identifier of the permission")
            @PathVariable @Positive @NotNull Long permissionId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Permission data required to update existing permission")
            @RequestBody @Valid PermissionRequest permissionRequest) {
        Permission permission = permissionService.updatePermission(permissionId, permissionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(permission);
    }

    @DeleteMapping("/permissionId")
    @Operation(summary = "Delete a permission", description = "Delete a specific permission by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Permission deleted successfully.",
                    content = @Content(mediaType = "text/plain") // o 204 No Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Permission not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<String> deletePermission(
            @Parameter(description = "Unique identifier of the permission")
            @PathVariable @Positive @NotNull Long permissionId) {
        String response = permissionService.deletePermissionById(permissionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
