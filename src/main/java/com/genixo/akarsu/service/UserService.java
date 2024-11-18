package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.User;
import com.genixo.akarsu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository repository;

    public User login(String username, String password) {
        return repository.findByLogin(username, password);
    }

    public List<User> findAll(Long type) {
        if (type == 1) {
            return repository.findByActive();
        }
        return repository.findAllUser();
    }


    public User add(User user) {
        return repository.saveAndFlush(user);
    }

    public User update(User user) {
        return repository.saveAndFlush(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public User setPassword(User user, String password) {
        user.setPassword(password);
        return repository.saveAndFlush(user);
    }




    /*
  personeller
    personellerEkle
    personellerGuncelle
    personellerSifre
    personellerSil
    login
     */
}
