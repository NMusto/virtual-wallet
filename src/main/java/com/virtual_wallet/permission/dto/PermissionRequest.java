package com.virtual_wallet.permission.dto;

import com.virtual_wallet.common.annotation.ValueOfEnum;
import com.virtual_wallet.common.enums.PermissionName;
import jakarta.validation.constraints.NotBlank;

public record PermissionRequest(
   @NotBlank
   @ValueOfEnum(enumClass = PermissionName.class, message = "Invalid Permission name")
   String permissionName
) {}
