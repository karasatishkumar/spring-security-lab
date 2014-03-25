
package org.techiekernel.service.user;

import java.util.Set;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.techiekernel.spring.user.CustomUserDetails;
import org.techiekernel.spring.user.CustomUserDetailsRepository;

public interface CustomUserDetailsService extends UserDetailsService {
    
    Set<CustomUserDetailsRepository.UserIdentifiers> getUsers();
    
    void deleteUser(long id);
    void upsertUser(CustomUserDetails user);
    
    CustomUserDetails getUser(long id);
    CustomUserDetails getUser(String username);
    
    

}
