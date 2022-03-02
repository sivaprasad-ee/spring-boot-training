package com.ee.springsecurity.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component("userDetailsService")
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<com.ee.springsecurity.demo.User> byUsername = userRepository.findByUsername(username);
        if(byUsername.isEmpty()){
            throw new UsernameNotFoundException("User with name:"+username+"not found");
        }
        final com.ee.springsecurity.demo.User domainUser = byUsername.get();
        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(domainUser.getRole()));
        return new User(domainUser.getUsername(), domainUser.getPassword(), authorities);

    }
}
