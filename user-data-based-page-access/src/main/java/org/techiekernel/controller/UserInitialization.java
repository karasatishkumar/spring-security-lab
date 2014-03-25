package org.techiekernel.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techiekernel.service.user.CustomUserDetailsInMemoryService;
import org.techiekernel.spring.user.CustomUserDetailsImpl;

@Component
public class UserInitialization {

    @Autowired
    private CustomUserDetailsInMemoryService pudsim;    
    
    @PostConstruct
    public void init() {
        
        this.pudsim.upsertUser(new CustomUserDetailsImpl("admin","admin"));
        this.pudsim.upsertUser(new CustomUserDetailsImpl("marc","1234"));
        
    }
    
}
