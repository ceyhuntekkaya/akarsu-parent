package com.genixo.akarsu.rest.api;

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

}
