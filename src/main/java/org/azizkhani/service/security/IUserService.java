package org.azizkhani.service.security;


import org.azizkhani.model.security.User;
import org.azizkhani.service.IGenericService;

public interface IUserService extends IGenericService<User> {
	public Boolean checkUserNameExistance(String userName);
	public User login(String userName,String passWord);
	public User findByUserName(String username);
}
