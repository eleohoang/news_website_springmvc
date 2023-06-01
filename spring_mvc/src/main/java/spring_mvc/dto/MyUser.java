package spring_mvc.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	public MyUser(String username, String password, boolean enabled, boolean accountNonExpired,
//			boolean credentialsNonExpired, boolean accountNonLocked,
//			Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//	}
	
	public MyUser(String username, String password, boolean enabled, 
			boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	private Long id;
	
	private String fullName;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
