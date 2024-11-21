package com.genixo.akarsu.rest.api;

import com.genixo.akarsu.common.exception.NotFoundException;
import com.genixo.akarsu.common.exception.ValidationException;
import com.genixo.akarsu.domain.*;
import com.genixo.akarsu.dto.LoginDto;
import com.genixo.akarsu.service.AuthorityService;
import com.genixo.akarsu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserRestController {

    final UserService userService;
    final AuthorityService authorityService;




    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Authority> documentSearch(@RequestBody LoginDto loginDto) {
        Authority user = authorityService.login(loginDto.getUsername(), loginDto.getPassword());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(value = "/staff/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> staff(@PathVariable("type") Long type) {
        List<User> result = userService.findAll(type);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User result = userService.addUser(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User patchUser) throws NotFoundException, ValidationException {
        User patchedUser = userService.updateUser(patchUser);
        return new ResponseEntity<>(patchedUser, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) throws NotFoundException {
        userService.deleteByUserId(userId);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
    @GetMapping(value = "/authority/{userId}/{authority}/{operation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Authority> updateAuthority(@PathVariable("userId") Long userId, @PathVariable("authority") String authority, @PathVariable("operation") Boolean operation) {
        Authority result = authorityService.updateAuthority(userId, authority, operation);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/authority/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Authority> getAuthority(@PathVariable Long userId) {
        Authority user = authorityService.getAuthority(userId);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
