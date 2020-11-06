package com.jurepinjuh.demo.Controllers;

import com.jurepinjuh.demo.Authenticatiion.JwtUtils;
import com.jurepinjuh.demo.Authenticatiion.UserDetailsImpl;
import com.jurepinjuh.demo.Controllers.HttpModels.LoginRequest;
import com.jurepinjuh.demo.Controllers.HttpModels.RegisterRequest;
import com.jurepinjuh.demo.Models.ERole;
import com.jurepinjuh.demo.Models.Role;
import com.jurepinjuh.demo.Models.User;
import com.jurepinjuh.demo.Repository.IRoleRepository;
import com.jurepinjuh.demo.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    IRoleRepository repository;

    @PostMapping("/auth/login")
    public String authenticateUser( @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return jwt;
    }
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUserName(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("User already exists");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("User already exists");
        }

        // Create new user's account
        User user= new User();
        Role role=new Role();
        role.setId(ERole.USER.value);
        user.setRole(role);
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encoder.encode(registerRequest.getPassword()));

        userRepository.AddUser(user);

        return ResponseEntity.ok().body(user.getId());
    }
}
