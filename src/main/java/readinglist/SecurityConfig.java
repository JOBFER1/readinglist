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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
//extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private ReadingListRepository readerRepository;

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
		
//            .and()
//            .hasRole("READER")
//            .requestMatchers("/**")
//            .permitAll()

        //            .and()
//            .formLogin(login -> login
//            			.loginPage("/login")
//                        .failureUrl("/login?error=true"))
//            ;
        
//		http
//		.authorizeHttpRequests()
//		.requestMatchers("/")
//		.hasRole("READER")
//		.requestMatchers("/**")
//		.permitAll()
//		.and()				
//		.formLogin()
//		.loginPage("/login")
//		.failureUrl("/login?error=true");
		

		
//      Spring Boot 2
//		.authorizeRequests()
//		.antMatchers("/")
//		.access("hasRole('READER')")
//		.antMatchers("/**")
		
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(new UserDetailsService() {
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				return readerRepository.findByReader(username);
//			}
//		});
//	}

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
