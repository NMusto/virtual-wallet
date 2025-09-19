package com.virtual_wallet.permission.dto;

import com.virtual_wallet.common.annotation.ValueOfEnum;
import com.virtual_wallet.common.enums.PermissionName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.Locale;

@Schema(description = "Data transfer object for creating or updating a Permission")
public record PermissionRequest(
   @NotBlank
   @ValueOfEnum(enumClass = PermissionName.class, message = "Invalid Permission name")
   @Schema(description = "Name of the permission to create or update", example = "READ_PERMISSION")
   String permissionName
) {}
