package com.virtual_wallet.permission.dto;

import com.virtual_wallet.common.annotation.ValueOfEnum;
import com.virtual_wallet.common.enums.PermissionName;
import jakarta.validation.constraints.NotBlank;

import java.util.Locale;

public record PermissionRequest(
   @NotBlank
   @ValueOfEnum(enumClass = PermissionName.class, message = "Invalid Permission name")
   String permissionName
) {
   public PermissionRequest {
      if (permissionName == null) throw new IllegalArgumentException("Permission name can not be null");
      permissionName = permissionName.trim().toUpperCase(Locale.ROOT);
   }
}
