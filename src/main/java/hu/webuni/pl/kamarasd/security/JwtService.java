package hu.webuni.pl.kamarasd.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtService {
	
	private Algorithm myAlg = Algorithm.HMAC256("secretPackage");
	private String myIssuer = "packageIssuer";
	private String myAuth = "auth";
	private Integer myLogInMinutes = 10;

	public String createJwtToken(UserDetails principal) {
		System.out.println("jwt");
		System.out.println(principal.getUsername());
		return JWT.create().withSubject(principal.getUsername())
				.withArrayClaim(myAuth, principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
				.withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(myLogInMinutes)))
				.withIssuer(myIssuer)
				.sign(myAlg);
	}
	
	public UserDetails parseJwt(String jwtToken) {
		DecodedJWT decodedJwt = JWT.require(myAlg).withIssuer(myIssuer).build().verify(jwtToken);
		
		return new User(decodedJwt.getSubject(), 
				"dummyParcel", 
				decodedJwt.getClaim(myAuth)
					.asList(String.class)
					.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList()));
	}

}
