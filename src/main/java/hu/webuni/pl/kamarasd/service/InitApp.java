package hu.webuni.pl.kamarasd.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hu.webuni.pl.kamarasd.model.DemoUser;

@Service
public class InitApp {
	
	public DemoUser createDemoUser(String username) {
		Map<String, DemoUser> demoUser = new HashMap<String, DemoUser>();

		demoUser.put("user1", new DemoUser(1L, "user1", new BCryptPasswordEncoder().encode("pass1"), "AddressManager"));
		demoUser.put("user2", new DemoUser(2L, "user2", new BCryptPasswordEncoder().encode("pass2"), "TransportManager"));
		demoUser.put("admin", new DemoUser(3L, "admin", new BCryptPasswordEncoder().encode("pass3"), "admin"));
		
		return demoUser.get(username) != null ?  demoUser.get(username) : null;
	}
	
}
