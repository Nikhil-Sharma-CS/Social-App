package org.example.Social.App.Controller;

import org.example.Social.App.Model.JwtRequest;
import org.example.Social.App.Model.JwtResponse;
import org.example.Social.App.Security.JwtHelper;
import org.example.Social.App.Service.CustomUserDetailService;
import org.example.Social.App.Service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private EmailService emailService;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    //Endpoint for login using email and password, and it sends the JWT token to your mail
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getUserEmail(), request.getUserPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .token(token)
                .username(userDetails.getUsername()).build();
        String text = "This is your token, please keep it safe : " + token;
        emailService.sendEmail(userDetails.getUsername(), text);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Authenticate email and password
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
