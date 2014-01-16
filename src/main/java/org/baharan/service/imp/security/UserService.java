package org.baharan.service.imp.security;

import java.util.Collection;
import java.util.Date;

import org.baharan.dao.IGenericRepository;
import org.baharan.dao.security.IUserRepository;
import org.baharan.model.line.LineStation;
import org.baharan.model.security.User;
import org.baharan.service.imp.GenericService;
import org.baharan.service.security.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User> implements IUserService{

	@Autowired(required=true)
	IUserRepository userRepository;
	
	@Override
	protected IGenericRepository<User> getGenericRepo() {
		return userRepository;
	}
	
	@Override
	public Boolean checkUserNameExistance(String userName) {
		return userRepository.checkUserNameExistance(userName);
	}

	@Override
	public User login(String userName, String passWord) {
		return userRepository.login(userName, passWord);
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}	
	
	
	@Override
	public void save(User entity) {
		if (entity.getId() > 0) {
			entity.setUpdatedDate(new Date());
			this.update(entity);
		} else {
			entity.setCreatedDate(new Date());
			entity.setUpdatedDate(new Date());
			this.add(entity);
		}
	}
}
