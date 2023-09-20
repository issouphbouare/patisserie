package com.fst.patisserie.bean;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fst.patisserie.model.Users;
import com.fst.patisserie.repository.UsersRepository;

@Named
public class AuthBean {

   public String username;
   public  Users currentUser;

    @Autowired
    UsersRepository usersRepository;

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        username = authentication.getName();
        return username;
    }

    public Users getCurrentUser() {
    	currentUser= usersRepository.findByUsername(getUsername());
    	return currentUser;
    }

}
