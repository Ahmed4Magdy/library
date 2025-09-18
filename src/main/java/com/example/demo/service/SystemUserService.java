package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.entity.SystemUser;
import com.example.demo.repo.SystemUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemUserService {

    private final SystemUserRepository systemUserRepository;

    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }


    public SystemUser addSystemUser(SystemUser user) {
        return systemUserRepository.save(user);
    }


    public SystemUser updateSystemUser(Long id, SystemUser user) {
        Optional<SystemUser> existSystemUser = systemUserRepository.findById(id);

        if (existSystemUser.isPresent()) {

            SystemUser existing = existSystemUser.get();
            existing.setUsername(user.getUsername());
            existing.setPassword(user.getPassword());
            existing.setFull_name(user.getFull_name());
            existing.setRole(user.getRole());
            return systemUserRepository.save(existing);
        }
        throw new RuntimeException("systemUser not found id "+ id);
    }

    public void deleteSystemUser(Long id) {
        systemUserRepository.deleteById(id);
    }

    public Optional<SystemUser> getSystemUserById(Long id) {
        return systemUserRepository.findById(id);
    }

    public List<SystemUser> getAllSystemUsers() {
        return systemUserRepository.findAll();
    }
}




