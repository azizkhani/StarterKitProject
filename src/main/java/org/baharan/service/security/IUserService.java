package org.baharan.service.security;


import org.baharan.model.security.User;
import org.baharan.service.IGenericService;

public interface IUserService extends IGenericService<User> {
	public Boolean checkUserNameExistance(String userName);
	public User login(String userName,String passWord);
	public User findByUserName(String username);
}
