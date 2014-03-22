
package org.techiekernel.spring.user;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.techiekernel.repository.CustomUserDetails;
import org.techiekernel.repository.CustomUserDetailsRepository;

public interface CustomUserDetailsService extends UserDetailsService {
    
    Set<CustomUserDetailsRepository.UserIdentifiers> getUsers();
    
    void deleteUser(long id);
    void upsertUser(CustomUserDetails user);
    
    CustomUserDetails getUser(long id);
    CustomUserDetails getUser(String username);

}
