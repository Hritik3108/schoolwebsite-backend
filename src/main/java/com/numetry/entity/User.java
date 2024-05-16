package com.numetry.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="SCHOOL_USER")
public class User extends AbstractEntity implements UserDetails {

	
//	@Column(name="FIRST_NAME")
	private String firstName; 
//	@Column(name="LAST_NAME")
	private String lastName;
//	@Column(name="EMAIL")
	private String email;
//	@Column(name="PASSWORD")
	private String password;
//	@Column(name="GENDER")
	private String gender;
//	@Column(name="CITY")
	private String city;
//	@Column(name="PINCODE")
	private String pincode;
	
//	@NonNull
	@Enumerated(EnumType.STRING)
	  private Role role;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
}
