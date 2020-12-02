package za.co.nedbank.javacoe.nedtweet_frontend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/login.html", "/css/*", "/js/*", "/images/*")
        .permitAll()
        .anyRequest().authenticated()
        .and()
//        .httpBasic();
        .formLogin()
        .loginPage("/login.html")
        .defaultSuccessUrl("/index.html", true)
        .permitAll()
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    List<UserDetails> users = new ArrayList<>();
    users.add(User.withDefaultPasswordEncoder().username("Sean Pleaner").password("password").roles("USER").build());
    return new InMemoryUserDetailsManager(users);
  }
}
