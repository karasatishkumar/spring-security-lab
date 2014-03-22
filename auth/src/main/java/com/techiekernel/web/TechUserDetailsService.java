package com.techiekernel.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.*;

@Component ("userService")
public class TechUserDetailsService implements UserDetailsService {

	private TechService techService;

	@Autowired
	public void setTechService(TechService techService) {
		this.techService = techService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = techService.findUserByUsername(username);
		return new TechUserDetails(user);
	}

	@SuppressWarnings("serial")
	public static class TechUserDetails implements UserDetails {

 		public static final String SCOPE_READ = "read";

		public static final String SCOPE_WRITE = "write";

 		public static final String ROLE_USER = "ROLE_USER";

		private Collection<GrantedAuthority> grantedAuthorities = new ArrayList
                  <GrantedAuthority>();

		private User user;

		public TechUserDetails(User user) {
			Assert.notNull(user, "the provided user reference can't be null");
			this.user = user;
			for (String ga : Arrays.asList(ROLE_USER, SCOPE_READ, SCOPE_WRITE)) {
				this.grantedAuthorities.add(new SimpleGrantedAuthority(ga));
			}
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return this.grantedAuthorities;
		}

		@Override
		public String getPassword() {
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			return user.getUserName();
		}

		@Override
		public boolean isAccountNonExpired() {
			return isEnabled();
		}

		@Override
		public boolean isAccountNonLocked() {
			return isEnabled();
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return isEnabled();
		}

		@Override
		public boolean isEnabled() {
			return user.isEnabled();
		}

		public User getUser() {
			return this.user;
		}
	}
}