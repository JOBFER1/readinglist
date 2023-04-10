package readinglist;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true) //No funcionará con los tests
@NoArgsConstructor
public class Reader implements UserDetails {
	
//	private static final long serialVersionUID = 1L;
	@Id
	private String username;
	private String fullname;
	private String password;

	public Reader(String username, String password) {
		this.username = username;
		this.password = password;
	}

// 	UserDetails methods
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("READER"));
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
}