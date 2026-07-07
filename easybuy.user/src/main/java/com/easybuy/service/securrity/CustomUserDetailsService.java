package com.easybuy.service.securrity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.easybuy.service.Exceptions.UserNotFound;
import com.easybuy.service.entity.User;
import com.easybuy.service.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
    private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepo.findByUserEmail(email).orElseThrow(()->new UserNotFound(String.format("user with this email: %s is not found", email)));
		Set<String> roles=new HashSet<>();
		roles.add("Admin");
		return new org.springframework.security.core.userdetails.User(user.getUserEmail(),user.getPassword(),userAuthorites(roles));
	}
	private Collection<? extends GrantedAuthority> userAuthorites(Set<String> roles){
		return roles.stream().map(
				role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}
	
  
}
