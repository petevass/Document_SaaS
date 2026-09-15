package com.mycompany.app.newgoogldocs.Controllers;

import com.mycompany.app.newgoogldocs.Data.Services.UserService;
import com.mycompany.app.newgoogldocs.Models.ApplicationUser;
import com.mycompany.app.newgoogldocs.Models.RegistrationForm;
import com.mycompany.app.newgoogldocs.Security.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class AuthenticationController {

    UserService userService;
    PasswordEncoder passwordEncoder;
    final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("form", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String submit(@Valid @ModelAttribute("form") RegistrationForm form, BindingResult errors, HttpServletRequest req, HttpServletResponse resp) {

        if(errors.hasErrors()){
            return "register";
        }
        if(userService.existsByEmail(form.getEmail())){
            errors.rejectValue("email", "error.email.exists", "Email already exists");
            return "register";
        }

        ApplicationUser user = new ApplicationUser();
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setEnabled(true);

        userService.addUser(user);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = userService.loadUserByUsername(form.getEmail());
        Authentication auth = UsernamePasswordAuthenticationToken.authenticated(
                userDetails, null, grantedAuthorities
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, req, resp);

        return "redirect:/dashboard";
    }



}
