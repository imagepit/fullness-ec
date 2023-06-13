package com.fullness.ec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fullness.ec.security.EmployeeAccountUserDetailsService;

/**
 * 担当者アカウントでのSpringSecurity設定クラス
 */
@Profile("production")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 担当者アカウントサービス
     */
    @Autowired
    EmployeeAccountUserDetailsService service;

    /**
     * パスワードエンコード設定メソッド
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * パスワードをエンコード処理するサービスを指定するメソッド
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
    }

    /**
     * 全体に対するセキュリティ設定メソッド
     * @param web セキュリティ設定（全体）オブジェクト
     * @throws Exception 例外クラス
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        // web.ignoring().antMatchers("/public/**");
    }

    /**
     * URLごとに異なるセキュリティ設定メソッド
     * @param http セキュリティ設定（URL毎）オブジェクト
     * @throws Exception 例外クラス
     */
    @Override
    public void configure(HttpSecurity http) throws Exception{
        // アクセス制限設定
        http.authorizeRequests()
                .antMatchers("/admin", "/admin/login").permitAll() // いつでも許可
                .antMatchers("/admin/logout").authenticated() // ログイン時に許可
                .antMatchers("/admin/**").hasRole("ADMIN");// ロール毎の許可
                //.antMatchers("/**").denyAll(); // それ以外はアクセス許可しない（認証が必要）
        http.formLogin()
                .loginPage("/admin/login") // ログイン画面のURL
                .loginProcessingUrl("/authenticate") // 認証処理URL
                .usernameParameter("username") // ユーザ名のパラメータ名（HTMLのtextboxのname属性と合わせる）
                .passwordParameter("password") // パスワードのパラメータ名（HTMLのtextboxのname属性と合わせる）
                .defaultSuccessUrl("/admin",true) // 認証成功時のURL
                .failureUrl("/admin/login") // 認証失敗時のURL
                .permitAll(); // ログイン認証はいつでも許可
        http.logout()
                .logoutUrl("/admin/logout") // ログアウトURL
                .logoutSuccessUrl("/admin") // ログアウト成功時のURL
                .invalidateHttpSession(true) // セッション破棄
                .deleteCookies("JSESSIONID") // 引数のCookie名のものを破棄
                .clearAuthentication(true) // 認証情報をクリア
                .permitAll(); // いつでもログアウトは許可
    }
}
