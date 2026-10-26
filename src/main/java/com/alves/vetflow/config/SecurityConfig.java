package com.alves.vetflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final TokenFilter tokenFilter;

    public SecurityConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login", "/auth/refresh").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/auth/delete").permitAll()

                        .requestMatchers(HttpMethod.POST, "/animal/criar").hasRole("")
                        .requestMatchers(HttpMethod.GET, "/animal/listar").hasRole("")
                        .requestMatchers(HttpMethod.PUT, "/animal/atualizar/{id}").hasRole("")
                        .requestMatchers(HttpMethod.DELETE, "/animal/deletar/{id}").hasRole("")

                        .requestMatchers(HttpMethod.POST, "/clinica/criar").hasRole("")
                        .requestMatchers(HttpMethod.GET, "/clinica/listar").hasRole("")
                        .requestMatchers(HttpMethod.PUT, "/clinica/atualizar/{id}").hasRole("")
                        .requestMatchers(HttpMethod.DELETE, "/clinica/deletar/{id}").hasRole("")

                        .requestMatchers(HttpMethod.POST, "/consulta/criar").hasRole("")
                        .requestMatchers(HttpMethod.GET, "/consulta/listar").hasRole("")
                        .requestMatchers(HttpMethod.PUT, "/consulta/atualizar/{id}").hasRole("")
                        .requestMatchers(HttpMethod.DELETE, "/consulta/deletar/{id}").hasRole("")

                        .requestMatchers(HttpMethod.POST, "/consultaprocedimento/criar").hasRole("")
                        .requestMatchers(HttpMethod.GET, "/consultaprocedimento/listar").hasRole("")
                        .requestMatchers(HttpMethod.PUT, "/consultaprocedimento/atualizar/{id}").hasRole("")
                        .requestMatchers(HttpMethod.DELETE, "/consultaprocedimento/deletar/{id}").hasRole("")

                        .requestMatchers(HttpMethod.POST, "/medicamento/criar").hasRole("")
                        .requestMatchers(HttpMethod.GET, "/medicamento/listar").hasRole("")
                        .requestMatchers(HttpMethod.PUT, "/medicamento/atualizar/{id}").hasRole("")
                        .requestMatchers(HttpMethod.DELETE, "/medicamento/deletar/{id}").hasRole("")

                        .requestMatchers(HttpMethod.POST, "/parcela/criar").hasRole("")
                        .requestMatchers(HttpMethod.GET, "/parcela/listar").hasRole("")
                        .requestMatchers(HttpMethod.PUT, "/parcela/atualizar/{id}").hasRole("")
                        .requestMatchers(HttpMethod.DELETE, "/parcela/deletar/{id}").hasRole("")

                        .requestMatchers(HttpMethod.POST, "/prescricao/criar").hasRole("")
                        .requestMatchers(HttpMethod.GET, "/prescricao/listar").hasRole("")
                        .requestMatchers(HttpMethod.PUT, "/prescricao/atualizar/{id}").hasRole("")
                        .requestMatchers(HttpMethod.DELETE, "/prescricao/deletar/{id}").hasRole("")



                        .anyRequest().authenticated())
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
