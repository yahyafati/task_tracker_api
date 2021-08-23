package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.RoleDao;
import com.yahya.task.tracker.tasktracker.model.security.Role;
import com.yahya.task.tracker.tasktracker.service.AuthorityService;
import com.yahya.task.tracker.tasktracker.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final AuthorityService authorityService;

    public RoleServiceImpl(RoleDao roleDao, AuthorityService authorityService) {
        this.roleDao = roleDao;
        this.authorityService = authorityService;
    }

    @Override
    public Role findById(int id) {
        return roleDao.findById(id).orElseThrow();
    }

    @Override
    public Role save(Role item) {
        item.setName(item.getName().toUpperCase());
        item.setAuthorities(
                item.getAuthorities().stream()
                        .map(authority -> authorityService.findByName(authority.getAuthority()))
                        .collect(Collectors.toSet())
        );
        item.getAuthorities().forEach(System.out::println);
        return roleDao.save(item);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        roleDao.deleteById(id);
        return true;
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
