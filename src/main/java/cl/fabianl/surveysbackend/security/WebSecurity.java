package cl.fabianl.surveysbackend.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cl.fabianl.surveysbackend.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
         * http
         * .cors().and()
         * .csrf().disable()
         * .authorizeRequests()
         * .antMatchers(HttpMethod.POST, "/users").permitAll()
         * .anyRequest().authenticated();
         * 
         * http
         * .authorizeRequests(authorizeRequests ->
         * authorizeRequests
         * .antMatchers("/users").permitAll()
         * .anyRequest().authenticated()
         * );
         */

        http
            //.cors(Customizer.withDefaults())
            .authorizeRequests(authorizeRequests -> authorizeRequests
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .antMatchers("/users").permitAll()
                    .anyRequest().authenticated())
            .csrf(csrf -> csrf.disable())
            .httpBasic(Customizer.withDefaults());

        http.addFilter(getAuthenticationFilter())
                .addFilter(new AuthenticationFilter(authenticationManager()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());

        authenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);

        return authenticationFilter;
    }

}
