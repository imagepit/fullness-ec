package com.fullness.ec.config;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import com.fullness.ec.security.EmployeeAccountUserDetailsService;

// @Profile("production")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  /**
   * 担当者アカウントサービス
   */
  @Autowired
  private EmployeeAccountUserDetailsService service;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(service).passwordEncoder(passwordEncoder);
  }

  @Bean
  public WebSecurityCustomizer webCustomizer() {
    DefaultHttpFirewall firewall = new DefaultHttpFirewall();
    return (web) -> web
      .httpFirewall(firewall)
      .ignoring()
      .requestMatchers("/img/**");
  }

  /**
   * Spring Securityの設定
   * 今回はログイン画面、ログアウト画面、認証対象外のURLを設定
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // ログイン設定
    http.formLogin(login -> login
      .loginPage("/admin/login")
      .loginProcessingUrl("/authenticate")
      .usernameParameter("username")
      .passwordParameter("password")
      .defaultSuccessUrl("/admin")
      .failureUrl("/admin/login")
      .permitAll()
    );
    // ログアウト設定
    http.logout(logout -> logout
      .logoutUrl("/admin/logout")
      .logoutSuccessUrl("/admin")
      .invalidateHttpSession(true)
      .deleteCookies("JSESSIONID")
      .clearAuthentication(true)
      .permitAll()
    );
    // 認証設定
    http.authorizeHttpRequests(authz -> authz
      .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
      .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
      .requestMatchers("/admin/login","/","/sample").permitAll()
      .requestMatchers("/admin/**").authenticated()
      .requestMatchers("/img/**").authenticated()
    );
    return http.build();
  }
}