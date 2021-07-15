package com.Yobulma.Yobulma.Service;

import com.Yobulma.Yobulma.Entity.Role;

public interface RoleService {
    Role saveRole(Role role);
    Role findByRoleName(String roleName);
    public Role addRole(Role role);
}
