package com.numetry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numetry.entity.AuthenticationRequest;
import com.numetry.entity.AuthenticationResponse;
import com.numetry.entity.ChangeUserPassword;
import com.numetry.entity.Token;
import com.numetry.entity.TokenType;
import com.numetry.entity.User;
import com.numetry.repository.SchoolWebsiteRepo;
import com.numetry.repository.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	@Autowired
	 private  SchoolWebsiteRepo repository;
	@Autowired
	 private  PasswordEncoder passwordEncoder;
	
	 @Autowired
	 private  JwtService jwtService;
	 @Autowired
	 private  AuthenticationManager authenticationManager;
	 @Autowired
	 private TokenRepository tokenRepository;

	public AuthenticationResponse register(User request) {
		// TODO Auto-generated method stub
//		var user = User.builder()
//		        .firstName(request.getFirstname())
//		        .lastName(request.getLastname())
//		        .email(request.getEmail())
//		        .password(passwordEncoder.encode(request.getPassword()))
//		        .role(request.getRole())
//		        .build();
//		User newUser=new User(request.getFirstName(),request.getLastName(),request.getEmail(),
//				passwordEncoder.encode(request.getPassword()),request.getRole());
		
		User newUser=new User(request.getFirstName(),request.getLastName(),request.getEmail(),
				passwordEncoder.encode(request.getPassword()),request.getGender(), request.getPincode(), request.getCity(), request.getRole());
		repository.save(newUser);
//		System.out.println(request.getFirstname()+" "+request.getEmail());
		var jwtToken = jwtService.generateToken(newUser);
		
		var refreshToken = jwtService.generateRefreshToken(newUser);
		saveUserToken(newUser, jwtToken);
		
		return AuthenticationResponse.builder()
		        .accessToken(jwtToken).refreshToken(refreshToken)
		        .userEmail(request.getEmail()).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
		System.out.println("Authentication initiated for user id:"+request.getEmail()+" password:"+request.getPassword()); 
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//			        request.getEmail(),
//		            request.getPassword()
//		        ));}
		    
		 
		 User user = repository.findByEmail(request.getEmail());
//		 System.out.println(user.getFirstName());
		    if (user==null) {
		        throw new Exception("User not found");
		    }
		    if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
		    	System.out.println("Password doesn't matches");
		    	throw new Exception("Password not found");
		    }
		 var jwtToken = jwtService.generateToken(user); 
		 System.out.println(jwtToken.toString());
		 var refreshToken = jwtService.generateRefreshToken(user);
		 System.out.println(refreshToken.toString());
		 revokeAllUserTokens(user);
		 saveUserToken(user, jwtToken);
		 
		 
		return  AuthenticationResponse.builder()
		        .accessToken(jwtToken).refreshToken(refreshToken)
		        .userEmail(request.getEmail()).build();
	}
	
	private void saveUserToken(User user, String jwtToken) {
	    var token = Token.builder()
	        .user(user)
	        .token(jwtToken)
	        .tokenType(TokenType.BEARER)
	        .expired(false)
	        .revoked(false)
	        .build();
	    tokenRepository.save(token);
	  }

	  private void revokeAllUserTokens(User user) {
	    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
	    if (validUserTokens.isEmpty()) {
	    	return;
	    }
	    validUserTokens.forEach(token -> {
	      token.setExpired(true);
	      token.setRevoked(true);
	    });
	    tokenRepository.saveAll(validUserTokens);
	  }

	  public void refreshToken(
	          HttpServletRequest request,
	          HttpServletResponse response
	  ) throws Exception {
	    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    final String refreshToken;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userEmail = jwtService.extractUsername(refreshToken);
	    if (userEmail != null) {
	      var user = this.repository.findByEmail(userEmail);
	              if(user==null) {
	            	  throw new Exception("User not found");
	              }
	      if (jwtService.isTokenValid(refreshToken, user)) {
	        var accessToken = jwtService.generateToken(user);
	        revokeAllUserTokens(user);
	        saveUserToken(user, accessToken);
	        var authResponse = AuthenticationResponse.builder()
	                .accessToken(accessToken)
	                .refreshToken(refreshToken)
	                .build();
	        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
	      }
	    }
	  }
	  
	  
	  public String ChangePassword(ChangeUserPassword request) {
		  try {
			  User user=repository.findByEmail(request.getEmail());
			  if(passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
			  	  user.setPassword(passwordEncoder.encode(request.getNewPassword()));
				  repository.save(user);
				  return "Password change successful";
			  }else {
				  return "Old password doesn't match";
			  }
		  }catch(Exception e) {
			  return "Something went Wrong";
		  }
	  }
	  
	  public boolean uniqueEmail(String email) {
		  if(repository.findByEmail(email)!=null) {
			  return false;
		  }
		  return true;
	  }

}

//fliter controller config role 
