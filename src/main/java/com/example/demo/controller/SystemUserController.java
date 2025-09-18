package com.example.demo.controller;


import com.example.demo.entity.SystemUser;
import com.example.demo.service.SystemUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/systemuser")
public class SystemUserController {


    private final SystemUserService systemUserService;


    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }


    @PostMapping("/add")
    public SystemUser addSystemUser(@RequestBody SystemUser user) {
        return systemUserService.addSystemUser(user);
    }

    @PutMapping("/{id}")
    public SystemUser updateSystemUser(@PathVariable Long id, @RequestBody SystemUser systemUser) {
        return systemUserService.updateSystemUser(id, systemUser);
    }


    @DeleteMapping("/{id}")
    public void deleteSystemUser(@PathVariable Long id) {
        systemUserService.deleteSystemUser(id);
    }

    @GetMapping("/{id}")
    public Optional<SystemUser> getSystemUserById(@PathVariable Long id) {
        return systemUserService.getSystemUserById(id);
    }

    @GetMapping("")
    public List<SystemUser> getAllSystemUsers() {
        return systemUserService.getAllSystemUsers();
    }


}
