package com.virtual_wallet.role.service;

import com.virtual_wallet.role.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();
    Role getRoleById(Long roleId);
    Role createRole(Role role);
    Role updateRole(Role role);
    String deleteRoleById(Long roleId);
}
