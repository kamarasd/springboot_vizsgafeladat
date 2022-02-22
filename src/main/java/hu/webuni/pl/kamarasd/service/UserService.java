package hu.webuni.pl.kamarasd.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		

return null;

//return new ParcelUser("Test", "$2a$10$cQ2eEw0dWyflSyqwiTyD6.WX5zwKS0QiCPSRTkrIlh5chyTF/prAG", Arrays.asList(new SimpleGrantedAuthority("USER")));
		
//		  if(username == demoUserDto.getUsername()) { 
//			  return new ParcelUser(username, demoUserDto.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER"))); } else { return
//						  (UserDetails) new UsernameNotFoundException(username); 
//		  }
		 
		
	}

}
