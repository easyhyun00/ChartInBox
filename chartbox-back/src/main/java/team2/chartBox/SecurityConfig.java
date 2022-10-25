//package team2.chartBox;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@Slf4j
//public class SecurityConfig {
//
//    @Bean
//    public BCryptPasswordEncoder encodePassword() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        log.info("Security config!!!!");
//
//        http.authorizeRequests()
//                .antMatchers("/login","/login/**","/logout","/join","/").permitAll()
//                .anyRequest().authenticated();
//        return http.build();
//    }
//
//
//}
