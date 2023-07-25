package com.rendShow.customerService.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customers implements UserDetails {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNo;
	@Column(unique = true)
	private String email;
	@Column(unique = true, length = 60)
	private String password;
	@Embedded
	private Address address;
	@OneToMany(mappedBy = "customers")
	//fetch = FetchType.EAGER,cascade = CascadeType.ALL
	private List<Device> devices = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "customers")
	private List<Token> tokens;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

//	private boolean enabled = false;


}
