package org.techiekernel.spring.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class CustomUserDetailsInMemoryRepository
    implements CustomUserDetailsRepository<CustomUserDetails> {
    
    private final Map<Long,CustomUserDetails> users
        = new HashMap<Long,CustomUserDetails>();

    private final Map<String,Long> usernamesToId
        = new HashMap<String,Long>();
    
    public CustomUserDetailsInMemoryRepository() { };

    @Override
    public void create(CustomUserDetails user) {
        
        if ( user == null ) return;
        if ( user.getUsername() == null ) return;
        if ( user.getUsername().isEmpty() ) return;
        
        synchronized(this) {
        
            // Checking id
            Long retr = this.usernamesToId.get(user.getUsername());
            
            if ( retr != null )
                throw new IllegalArgumentException("User already exists");
            
            this.users.put(user.getId(), user);
            this.usernamesToId.put(user.getUsername(), user.getId());
            
        }
        
    }

    @Override
    public CustomUserDetails read(String username) {
        
        if ( username == null ) return null;
        if ( username.isEmpty() ) return null;
        
        synchronized(this) {
        
            // Checking id
            Long retr = this.usernamesToId.get(username);
            
            if ( retr == null ) return null;
            
            return this.users.get(retr);
            
        }
        
    }

    @Override
    public CustomUserDetails read(long id) {
        return this.users.get(id);
    }

    @Override
    public void update(CustomUserDetails user) {
        
        if ( user == null ) return;
        
        synchronized(this) {
            
            CustomUserDetails retr = this.users.get(user.getId());
            
            if ( retr == null) 
                throw new IllegalArgumentException("User does not exist");
            
            if ( retr.getUsername().equals(user.getUsername()) )
                throw new IllegalArgumentException("User name mismatch: "
                    + retr.getUsername() + ", " + user.getUsername());
            
            this.users.put(user.getId(), user);
            this.usernamesToId.put(user.getUsername(), user.getId());
            
        }
        
    }

    @Override
    public void delete(String username) {
        
        synchronized(this) {
            
            Long retr = this.usernamesToId.get(username);
            
            if ( retr == null ) return;
            
            this.users.remove(retr);
            this.usernamesToId.remove(username);
            
        }
        
    }

    @Override
    public void delete(long id) {
        
        synchronized(this) {
            
            CustomUserDetails retr = this.users.get(id);
            if ( retr == null ) return;
            
            this.users.remove(id);
            this.usernamesToId.remove(retr.getUsername());
            
        }
        
    }

    @Override
    public boolean contains(CustomUserDetails user) {
        return this.users.containsKey(user.getId());
    }

    public static class UserIdentifiersImpl implements UserIdentifiers {
        
        private final String username;
        private final long id;
        
        public UserIdentifiersImpl(String username, long id) {
            this.username = username;
            this.id = id;
        }

        @Override
        public long getId() {
            return this.id;
        }

        @Override
        public String getUsername() {
            return this.username;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + (this.username != null ? this.username.hashCode() : 0);
            hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            
            if ( obj == this ) return true;
            if ( obj == null ) return false;
            
            if ( !UserIdentifiers.class.isAssignableFrom(obj.getClass()) )
                return false;
            
            final UserIdentifiers other = (UserIdentifiers) obj;
            if ((this.username == null) ? (other.getUsername() != null) : !this.username.equals(other.getUsername())) {
                return false;
            }
            if (this.id != other.getId()) {
                return false;
            }
            return true;
        }
        
    }
    
    @Override
    public Set<UserIdentifiers> getUsers() {
        
        HashSet<UserIdentifiers> result = new HashSet<UserIdentifiers>();
        
        synchronized(this) {
            for ( CustomUserDetails ui : this.users.values() ) {
                result.add(new UserIdentifiersImpl(ui.getUsername(), ui.getId()));
            }
        }
        
        return result;
        
    }
    
}
