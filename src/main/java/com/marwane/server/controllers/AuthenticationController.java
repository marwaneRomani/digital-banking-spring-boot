package com.marwane.server.controllers;

import com.marwane.server.dtos.login.LoginReponse;
import com.marwane.server.dtos.login.LoginUserDto;
import com.marwane.server.dtos.register.RegisterUserDto;
import com.marwane.server.models.users.User;
import com.marwane.server.service.AuthenticationService;
import com.marwane.server.service.UserDetailsServiceImpl;
import com.marwane.server.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationService authenticationService,AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationService = authenticationService;

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/register-client")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/register-agent")
    public ResponseEntity<User> registerManager(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signupAgent(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginUserDto loginReq)  {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));

            UserDetailsServiceImpl user = (UserDetailsServiceImpl) authentication.getPrincipal();

            String token = jwtUtil.generateToken(user);
            LoginReponse loginRes = new LoginReponse(user.getUsername(),token);

            return ResponseEntity.status(HttpStatus.OK).body(loginRes);
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("status", 403, "message", "Nom d'utilisateur ou mot de passe incorrect"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", 500, "message", "Une erreur interne s'est produite"));
        }
    }
}