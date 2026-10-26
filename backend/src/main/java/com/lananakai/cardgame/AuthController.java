package com.lananakai.cardgame;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Sign up, log in, and check who is logged in. (Logout is handled in SecurityConfig.)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // what the frontend sends to register / log in
    public record Credentials(String username, String password) {
    }

    // what we send back about a user (never the password hash)
    public record UserInfo(String username) {
    }

    private final AppUserRepository users;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final SecurityContextRepository contextRepository;

    public AuthController(AppUserRepository users, PasswordEncoder passwordEncoder,
            AuthenticationManager authManager, SecurityContextRepository contextRepository) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.contextRepository = contextRepository;
    }

    // POST create an account, then log in as it
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Credentials creds,
            HttpServletRequest request, HttpServletResponse response) {
        String username = creds.username() == null ? "" : creds.username().trim();
        String password = creds.password() == null ? "" : creds.password();
        if (username.isEmpty() || password.length() < 8) {
            return ResponseEntity.badRequest().body("Username is required and password must be at least 8 characters");
        }
        if (users.existsByUsername(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("That username is taken");
        }
        users.save(new AppUser(username, passwordEncoder.encode(password)));
        return login(new Credentials(username, password), request, response);
    }

    // POST check the password and, if it matches, remember the user in their session
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials creds,
            HttpServletRequest request, HttpServletResponse response) {
        Authentication auth;
        try {
            auth = authManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(creds.username(), creds.password()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password");
        }

        // give the session a new id so a session id from before login can't be reused
        if (request.getSession(false) != null) {
            request.changeSessionId();
        }
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
        contextRepository.saveContext(context, request, response);

        return ResponseEntity.ok(new UserInfo(auth.getName()));
    }

    // GET the logged-in user, or 401 if nobody is logged in
    @GetMapping("/me")
    public ResponseEntity<UserInfo> me(Principal principal) {
        if (principal == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(new UserInfo(principal.getName()));
    }
}
