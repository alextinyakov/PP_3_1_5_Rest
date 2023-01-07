package ru.kata.spring.boot_security.demo.Service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private List<Role> roles;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        if (roles == null) {
            roles = roleRepository.findAll();
        }
        return roles;
    }

    public Set<Role> getSetRoles(Long[] roleId) {
        return roleRepository.findByIdIn(roleId);
    }
}