package com.mycompany.app.newgoogldocs.Security;

import com.mycompany.app.newgoogldocs.Data.Services.UserService;
import com.mycompany.app.newgoogldocs.Models.ApplicationUser;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    UserService userService;
    PasswordEncoder passwordEncoder;

    @Transactional
    public ApplicationUser register(String email, String password) {
        if(userService.existsByEmail(email)){
            throw new UsernameTakenException(email);
        }
        ApplicationUser user = new ApplicationUser();
        user.setEmail(email);
        user.setPassword(password);
        userService.addUser(user);
        return user;
    }

    public static class UsernameTakenException extends RuntimeException{
        public UsernameTakenException(String username) {
            super("Username already registered: "+username);
        }
    }

}
