package org.techiekernel.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.techiekernel.spring.user.CustomUserDetails;

@Service("pap")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomUserDetailsInMemoryService pudsim;
    
    public CustomAuthenticationProvider() { }
    
    @Override
    @SuppressWarnings({"null", "ConstantConditions"})
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        
        if ( authentication == null ) return null;
        if ( !supports(authentication.getClass()) ) return null;
        
        UsernamePasswordAuthenticationToken retr
            = (UsernamePasswordAuthenticationToken) authentication;
        
        String principal = (String) retr.getPrincipal();
        String password = (String) retr.getCredentials();
        
        CustomUserDetails user = null;
        
        try {
        
            user = pudsim.loadUserByUsername(principal);
            
            System.out.println("User : " + user);
            
        } catch(UsernameNotFoundException ex) {
            
             throw new BadCredentialsException(
                 principal + " principal not found", ex);
            
        }
        
        // Returned user is never null
        if ( !user.isEnabled() ) {
            throw new DisabledException("Username: " + user.getUsername());
        }
        
        if ( !user.isAccountNonLocked() ) {
            throw new LockedException("Username: " + user.getUsername());
        }
        
        if ( !user.getPassword().equals(password) ) {
            throw new BadCredentialsException("Username: " + user.getUsername());
        }
        
        return new UsernamePasswordAuthenticationToken(
            user.getUsername(), user.getPassword(), user.getAuthorities());
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        
        if ( authentication == null ) return false;
        
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
                
    }
    
    public static CustomUserDetails getLoggedInUser() {
    
        // Anyone logged in?
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if ( auth == null ) {
            
            // Nope
            return null;
            
        } else {
            
            Object princ = auth.getPrincipal();

            if (princ instanceof CustomUserDetails) {
                return (CustomUserDetails) princ;
            } else {
                
                // That is a big bug !!!
                return null;
                
            }
            
        }
        
    }
        
}
