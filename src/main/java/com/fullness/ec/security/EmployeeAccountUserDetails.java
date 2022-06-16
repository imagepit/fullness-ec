package com.fullness.ec.security;

import com.fullness.ec.entity.EmployeeAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class EmployeeAccountUserDetails implements UserDetails {

    private final EmployeeAccount account;
    private final Collection<GrantedAuthority> authorites;

    public EmployeeAccountUserDetails(EmployeeAccount account,Collection<GrantedAuthority> authorites){
        this.account = account;
        this.authorites = authorites;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorites;
    }


    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
