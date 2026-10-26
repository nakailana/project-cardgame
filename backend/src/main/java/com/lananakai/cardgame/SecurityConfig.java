package com.lananakai.cardgame;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

// Decides who can reach which URLs, and how logins are checked.
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // anyone can sign up, log in, or ask "am I logged in?"
                        .requestMatchers("/api/auth/register", "/api/auth/login", "/api/auth/me").permitAll()
                        // dev only; in prod the console is turned off, so this path is just a 404
                        .requestMatchers("/h2-console/**").permitAll()
                        // everything else needs a logged-in user
                        .anyRequest().authenticated())
                // the session cookie is SameSite=Strict (application.properties), so other
                // sites can't make requests with it; that covers what CSRF tokens protect against
                .csrf(csrf -> csrf.disable())
                // the H2 console runs inside a frame
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                // reply 401 instead of redirecting to a login page (the frontend shows its own)
                .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()));
        return http.build();
    }

    // hashes passwords with BCrypt (slow on purpose, and salted, so leaked hashes are hard to crack)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // tells Spring Security how to look up a user by username in our database
    @Bean
    public UserDetailsService userDetailsService(AppUserRepository users) {
        return username -> users.findByUsername(username)
                .map(u -> User.withUsername(u.getUsername()).password(u.getPasswordHash()).build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    // checks a username + password against the stored hash
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }

    // remembers who is logged in by storing it in their HTTP session
    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }
}
