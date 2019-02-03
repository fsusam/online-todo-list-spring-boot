package com.fsusam.tutorial.security;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsusam.tutorial.persist.domain.User;
import com.fsusam.tutorial.persist.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody final AuthenticationRequest data) {

        try {
            final String username = data.getUsername();
            LOGGER.info("username:{}", username);
            LOGGER.info("password:{}", data.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            final User user = users.findByUsername(username);
            LOGGER.info("user:{}", user);
            if (user == null) {
                throw new UsernameNotFoundException("Username " + username + "not found");
            }
            final String token = jwtTokenProvider.createToken(username, new UserPrincipal(user).getRoles());

            final Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (final AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/check")
    public ResponseEntity check() {
        LOGGER.info("check...");
        final Map<Object, Object> model = new HashMap<>();
        model.put("message", "SUCCESS");
        return ok(model);
    }
}
