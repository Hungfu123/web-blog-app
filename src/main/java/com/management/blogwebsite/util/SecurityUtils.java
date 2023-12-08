package com.management.blogwebsite.util;

import jakarta.persistence.Column;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUtils {

    // Holen uns den current LogIn User von SecurityContextHolder
    public static User getCurrentUser(){
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principle instanceof User){
            return (User) principle;
        }
        return null;
    }

    // Rückgabewert ist die vorhandene eingeloggte User ROlle
    public static String getRole(){
        User user =getCurrentUser();
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        for(GrantedAuthority authority: authorities){
            return authority.getAuthority();
        }
        return null;
    }
}
