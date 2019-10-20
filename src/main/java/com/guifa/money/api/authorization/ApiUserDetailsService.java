package com.guifa.money.api.authorization;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.guifa.money.api.model.User;
import com.guifa.money.api.repository.user.UserRepository;

@Service
public class ApiUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username and/or password incorrects"));
		
		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), getAuthorities(user));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getDescription().toUpperCase())));
		
		return authorities;
	}

}
