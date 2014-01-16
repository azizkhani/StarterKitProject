package org.baharan.service.security;

/*
 * spring have a User object.
 * this class map the AppUser(userEntity object) to the Spring user object
 *   
 */

import java.util.HashSet;
import java.util.Set;

import org.baharan.model.security.Action;
import org.baharan.model.security.Group;
import org.baharan.model.security.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assembler")
public class Assembler {

	@Transactional(readOnly = true)
	public User buildUserFromUserEntity(User userEntity) {
		String username = userEntity.getUserName();
		String password = userEntity.getPassWord();
		long id = userEntity.getId();
		boolean enabled = userEntity.getIsActive();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Set<GrantedAuthority> authorities = new HashSet();
		authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		if(userEntity.getGroups()!=null){
			for (Group group : userEntity.getGroups()) {
				for (Action action : group.getActions()) {
					authorities.add(new GrantedAuthorityImpl("ROLE_"+action.getId()));
				}
			}
		}
		userEntity.setAuthorities(authorities);
		
		/*org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired,credentialsNonExpired, accountNonLocked, 
				authorities);
				*/
		return userEntity;
	}
}
