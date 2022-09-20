package com.SECURITY.securityTake2.config;

import com.SECURITY.securityTake2.JWT.JwtConfig;
import com.SECURITY.securityTake2.JWT.JwtTokenVerifier;
import com.SECURITY.securityTake2.JWT.JwtUsernamePasswordAuthFilter;
import com.SECURITY.securityTake2.model.UserPermission;
import com.SECURITY.securityTake2.model.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public SecurityConfig(SecretKey secretKey, JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    //  Method for configuring path's.
    //  Who has which permissions and what kind of authorization we want to use.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordAuthFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernamePasswordAuthFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/api/**").hasRole(UserRole.STUDENT.name())
                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.ADMINTRAINEE.name())
                .anyRequest().authenticated();
    }

    //  Method for configuring users.
    //  We can predefine some users in memory or in database, in this case it is in memory.
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails AnaSmith = User.builder()
                .username("AnaSmith")
                .password(passwordEncoder().encode("123456"))
//                .roles(UserRole.STUDENT.name())
                .authorities(UserRole.STUDENT.getGrantedAuthorities())
                .build();
        UserDetails PereSmith = User.builder()
                .username("PereSmith")
                .password(passwordEncoder().encode("123456"))
//                .roles(UserRole.STUDENT.name())
                .authorities(UserRole.STUDENT.getGrantedAuthorities())
                .build();
        UserDetails MarijaDoe = User.builder()
                .username("MarijaSmith")
                .password(passwordEncoder().encode("123456"))
//                .roles(UserRole.STUDENT.name())
                .authorities(UserRole.STUDENT.getGrantedAuthorities())
                .build();
        UserDetails Linda = User.builder()
                .username("Linda")
                .password(passwordEncoder().encode("123456"))
//                .roles(UserRole.ADMIN.name())
                .authorities(UserRole.ADMIN.getGrantedAuthorities())
                .build();
        UserDetails Tom = User.builder()
                .username("Tom")
                .password(passwordEncoder().encode("123456"))
//                .roles(UserRole.ADMINTRAINEE.name())
                .authorities(UserRole.ADMINTRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                AnaSmith,
                PereSmith,
                MarijaDoe,
                Linda,
                Tom
        );
    }

    //  Method for password encoder.
    //  Which type of password encoder we want to use, or we don't use none.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
