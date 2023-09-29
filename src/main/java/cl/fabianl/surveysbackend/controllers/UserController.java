package cl.fabianl.surveysbackend.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import cl.fabianl.surveysbackend.entities.UserEntity;
import cl.fabianl.surveysbackend.models.requets.UserRegisterRequestModel;
import cl.fabianl.surveysbackend.models.responses.UserRest;
import cl.fabianl.surveysbackend.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserService userService;
    @PostMapping
    public UserRest createUser(@RequestBody @Valid UserRegisterRequestModel userModel) {
        UserEntity user = userService.createUser(userModel);

        UserRest userRest = new UserRest();

        BeanUtils.copyProperties(user, userRest);

        return userRest;    
    }

    @GetMapping
    public UserRest getUser(Authentication authentication) {
        System.out.println(authentication);

        return null;
    }
    
    
}
