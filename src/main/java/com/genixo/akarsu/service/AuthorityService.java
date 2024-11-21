package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.Authority;
import com.genixo.akarsu.domain.User;
import com.genixo.akarsu.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    /*
     yetkiler
    yetkilerGuncelle
     */

    private final AuthorityRepository repository;

    public List<Authority> findAll() {
        return repository.findAll();
    }

    public Authority findByUserId(Long userId) {
        Authority authority = repository.findByUserId(userId);
        if (authority == null) {
            User user = new User();
            user.setId(userId);
            authority = new Authority();
            authority.setUser(user);
            authority.setFileSearch(false);
            authority.setArchive(false);
            authority.setScan(false);
            authority.setAdmin(false);
            authority.setText(false);
            authority.setProject(false);
            authority.setDelete(false);
            return repository.saveAndFlush(authority);
        }
        return authority;
    }

    public Authority update(Authority authority) {

        return repository.saveAndFlush(authority);
    }

    public Authority add(Authority authority) {

        return repository.saveAndFlush(authority);
    }

    public Authority login(String username, String password) {
        return repository.login(username, password);
    }


    public Authority updateAuthority(Long userId, String authority, Boolean operation) {
        Authority auth = findByUserId(userId);
        switch (authority) {
            case "fileSearch":
                auth.setFileSearch(operation);
                break;
            case "archive":
                auth.setArchive(operation);
                break;
            case "scan":
                auth.setScan(operation);
                break;
            case "admin":
                auth.setAdmin(operation);
                break;
            case "text":
                auth.setText(operation);
                break;
            case "project":
                auth.setProject(operation);
                break;
            case "delete":
                auth.setDelete(operation);
                break;
        }
        return update(auth);
    }

    public Authority getAuthority(Long userId) {
        return repository.getAuthority(userId);
    }
}
