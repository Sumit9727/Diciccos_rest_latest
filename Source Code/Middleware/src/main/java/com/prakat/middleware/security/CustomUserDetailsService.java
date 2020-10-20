package com.prakat.middleware.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prakat.middleware.entity.User;
import com.prakat.middleware.exception.BussinessException;
import com.prakat.middleware.repository.UserRepository;

/**
 * Created by Amit 
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BussinessException("User not found with email : " + email)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserPrincipal loadUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new BussinessException("User not found with id : "+ id)
        );

        return UserPrincipal.create(user);
    }
    public Integer getUserId() {
    	UserPrincipal userDetails = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if(userDetails !=null)
    		return userDetails.getId();
    	return null;
    	
    }
}