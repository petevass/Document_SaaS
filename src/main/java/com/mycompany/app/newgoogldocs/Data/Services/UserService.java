package com.mycompany.app.newgoogldocs.Data.Services;

import com.mycompany.app.newgoogldocs.Data.Repositories.ApplicationUserRepository;
import com.mycompany.app.newgoogldocs.Models.ApplicationUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements  UserDetailsService {


    ApplicationUserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Bad credentials");
        }

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .disabled(!user.isEnabled())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
    }

    public void addUser(ApplicationUser applicationUser){
        userRepository.save(applicationUser);
    }

    public ApplicationUser findUserByEmail(String email){return userRepository.findByEmail(email);}

    public void deleteUser(ApplicationUser applicationUser){
        userRepository.delete(applicationUser);
    }
    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }

    public ApplicationUser findUserById(String id){
        return userRepository.findById(id).orElse(null);
    }

    public List<ApplicationUser> findAllUser(){
        return userRepository.findAll();
    }

    public boolean existsByEmail(String email){return userRepository.existsByEmail(email);}
}
