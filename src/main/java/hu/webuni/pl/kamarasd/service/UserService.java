package hu.webuni.pl.kamarasd.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hu.webuni.pl.kamarasd.model.DemoUser;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	InitApp initApp;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DemoUser demoUser = initApp.createDemoUser(username);
		
		if(demoUser != null) {
			return new User(demoUser.getUsername(), demoUser.getPassword(), Arrays.asList(new SimpleGrantedAuthority(demoUser.getRole())));	
		} else {
			throw new UsernameNotFoundException("Username not found: " + username);
		}
		

	}

}
