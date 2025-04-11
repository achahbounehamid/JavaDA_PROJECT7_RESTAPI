package com.nnk.springboot.security;

import com.nnk.springboot.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/**
 * Configuration de la sécurité de l'application.
 *
 * Cette classe configure :
 * <ul>
 *     <li>Les routes accessibles sans authentification</li>
 *     <li>Le formulaire de login personnalisé (/app/login)</li>
 *     <li>Le logout personnalisé (/app/logout)</li>
 *     <li>L'encodage des mots de passe avec BCrypt</li>
 *     <li>La gestion des erreurs 403 (/app/error)</li>
 * </ul>
 *
 * L'application utilise une méthode d'authentification basée sur la session (via le cookie JSESSIONID).
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    /**
     * Constructeur injectant le service personnalisé de gestion des utilisateurs.
     *
     * @param userDetailsService service utilisé pour charger les utilisateurs à partir de la base de données
     */
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    /**
     * Définit la chaîne de filtres de sécurité HTTP de Spring Security.
     *
     * @param http l'objet de configuration HTTP
     * @return un objet {@link SecurityFilterChain} configuré
     * @throws Exception en cas d'erreur de configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/app/login",
                                "/css/**",
                                "/js/**",
                                "/api/user/add",
                                "/error"
                        ).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/app/login")
                        .loginProcessingUrl("/app/login")
                        .defaultSuccessUrl("/bidList/list", true)
                        .failureUrl("/app/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/app/logout"))
                        .logoutSuccessUrl("/app/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .userDetailsService(userDetailsService)
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/403")
                );

        return http.build();
    }
    /**
     * Fournit un encodeur de mot de passe basé sur l'algorithme BCrypt.
     *
     * @return une instance de {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * Fournit l'AuthenticationManager utilisé pour l'authentification des utilisateurs.
     *
     * @param authConfig la configuration d'authentification de Spring
     * @return une instance de {@link AuthenticationManager}
     * @throws Exception en cas d'erreur de récupération
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}