package com.adil.anoteapi.service;


import com.adil.anoteapi.entity.User;
import com.adil.anoteapi.entity.UserPrincipal;
import com.adil.anoteapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        User user = repo.findByUsernameOrEmail(input)
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        return new UserPrincipal(user);
    }
}
