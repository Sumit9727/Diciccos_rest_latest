package com.prakat.middleware.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.prakat.middleware.security.CustomUserDetailsService;
import com.prakat.middleware.security.JwtAuthenticationEntryPoint;
import com.prakat.middleware.security.TokenAuthenticationFilter;
import com.prakat.middleware.security.oauth2.CustomOAuth2UserService;
import com.prakat.middleware.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.prakat.middleware.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.prakat.middleware.security.oauth2.OAuth2AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
		)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

	@Autowired
	private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public TokenAuthenticationFilter tokenAuthenticationFilter() {
	        return new TokenAuthenticationFilter();
	    }

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable().cors().and()
				/* Enable SSL @Production Environment
				 * .requiresChannel() .anyRequest() .requiresSecure() .and()
				 */
		// dont authenticate this particular request
		.authorizeRequests().antMatchers("/auth/**","/otp/verify",
				"/v2/api-docs","/configuration/**", "/swagger*/**","webjars/springfox-swagger-ui/**","/documentation/**",
				"/swagger-resources/**", "/swagger-ui/**","/swagger-resources/configuration/security",
				"/swagger-resources/configuration/ui", "/webjars/**", "/csrf", "/", "/h2-console/**").permitAll()
		// all other requests need to be authenticated
		.antMatchers("/auth/**", "/oauth2/**","/token","/login","/register","/login/**").permitAll()
		.antMatchers(HttpMethod.GET, "/dishes/**","/menus/**","/restaurants","/restaurants/**","/refreshtoken/**","/search/**").permitAll()
		.antMatchers(HttpMethod.POST, "/dishes/**","/menus/**","/restaurants","/restaurants/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers(HttpMethod.PUT, "/dishes/**","/menus/**","/restaurants","/restaurants/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers(HttpMethod.DELETE, "/dishes/**","/menus/**","/restaurants","/restaurants/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest().authenticated().and()
		// make sure we use stateless session; session won't be used to
		// store user's state.
		.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.oauth2Login()
		.authorizationEndpoint()
		.baseUri("/oauth2/authorize")
		.authorizationRequestRepository(cookieAuthorizationRequestRepository())
		.and()
		.redirectionEndpoint()
		.baseUri("/oauth2/callback/*")
		.and()
		.userInfoEndpoint()
		.userService(customOAuth2UserService)
		.and()
		.successHandler(oAuth2AuthenticationSuccessHandler)
		.failureHandler(oAuth2AuthenticationFailureHandler);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
