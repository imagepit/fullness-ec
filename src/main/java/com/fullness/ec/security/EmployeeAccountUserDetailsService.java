package com.fullness.ec.security;

import com.fullness.ec.entity.EmployeeAccount;
import com.fullness.ec.repository.EmployeeAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmployeeAccountUserDetailsService implements UserDetailsService {
    @Autowired
    EmployeeAccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeAccount employeeAccount = repository.findByUsername(username);
        if(employeeAccount == null) throw new UsernameNotFoundException("user not found");
        Collection<GrantedAuthority> authorities = getAuthority(employeeAccount);
        return new EmployeeAccountUserDetails(employeeAccount,authorities);
    }

    private Collection<GrantedAuthority> getAuthority(EmployeeAccount account){
        return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
    }
}
