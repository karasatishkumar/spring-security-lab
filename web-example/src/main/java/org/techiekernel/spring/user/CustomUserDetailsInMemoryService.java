package org.techiekernel.spring.user;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.techiekernel.repository.CustomUserDetails;
import org.techiekernel.repository.CustomUserDetailsInMemoryRepository;
import org.techiekernel.repository.CustomUserDetailsRepository;

@Service("customUserDetailsInMemoryService")
public class CustomUserDetailsInMemoryService implements CustomUserDetailsService {

    @Autowired
    private CustomUserDetailsInMemoryRepository pud; 
    
    public CustomUserDetailsInMemoryService() { }

    @Override
    public void deleteUser(long id) {
        this.pud.delete(id);
    }

    @Override
    public void upsertUser(CustomUserDetails user) {
        
        if ( this.pud.contains(user) ) {
            this.pud.update(user);
        } else {
            this.pud.create(user);
        }
        
    }
    
    @Override
    public CustomUserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        
    	CustomUserDetails retr = this.pud.read(username);
        
        if ( retr == null )
            throw new UsernameNotFoundException(username);
        
        return retr;
        
    }

    @Override
    public CustomUserDetails getUser(long id) {
        return this.pud.read(id);
    }

    @Override
    public CustomUserDetails getUser(String username) {
        return this.pud.read(username);
    }

    @Override
    public Set<CustomUserDetailsRepository.UserIdentifiers> getUsers() {
        return this.pud.getUsers();
    }

}
