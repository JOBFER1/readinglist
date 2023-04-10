package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
			.csrf()
			.ignoringRequestMatchers(PathRequest.toH2Console())
			.and()
			.headers().frameOptions().sameOrigin()
			.disable()

        	.authorizeHttpRequests()
            .requestMatchers("/")
//            .requestMatchers("/api/pepe")
            .permitAll()
            .anyRequest()
            .authenticated()
			
//			.hasRole("READER")
            
            .and()
            .formLogin()
            .and()
            .httpBasic()
            
//    		.loginPage("/login")
//    		.failureUrl("/login?error=true")
    		;

		http.authenticationProvider(authenticationProvider());
		
		return http.build();
		
	}

	@Autowired
	private CustomUserDetailsService userDetailsService;
		
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
	
}
