package cl.fabianl.surveysbackend.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import cl.fabianl.surveysbackend.entities.UserEntity;
import cl.fabianl.surveysbackend.models.requets.UserRegisterRequestModel;

public interface UserService extends UserDetailsService {
    public UserDetails loadUserByUsername(String email);
    
    public UserEntity createUser(UserRegisterRequestModel userRegisterRequestModel);
}
