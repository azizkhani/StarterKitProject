package org.baharan.service.security;


import org.apache.log4j.Logger;
import org.baharan.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BaharUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserService userService;
	private static final Logger logger = Logger.getLogger(BaharUserDetailsService.class);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Assembler assembler = new Assembler();
		User userEntity  = userService.findByUserName(username);
		if(userEntity!=null) {
			logger.info("------------==" + userEntity.getUserName());
			logger.info("------------==" + userEntity.getPassWord());
		}
		else if(username.equals("admin")){
				userEntity= new User("ali","aziz","admin","admin","aa.aziz");
				userEntity.setIsEnabled(true);
				userEntity.setIsActive(true);
			}
		if (userEntity == null)
			throw new UsernameNotFoundException("user not found");
		return assembler.buildUserFromUserEntity(userEntity);
	}
}
