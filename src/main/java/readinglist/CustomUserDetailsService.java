package readinglist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private ReaderRepository readerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Reader reader = readerRepository.findByUsername(username);
//				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username: " + userName));

		//return new User(user.getPassword(), mapearRoles(user.getAuthorities()));
		return new Reader(reader.getUsername(), reader.getUsername(), reader.getPassword());
	}

//	private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles) {
//		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
//	}
}
