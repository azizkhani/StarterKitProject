package org.baharan.dao.security;


import org.baharan.dao.IGenericRepository;
import org.baharan.model.security.User;



public interface IUserRepository extends  IGenericRepository<User>{
	public Boolean checkUserNameExistance(String userName);
	public User login(String userName,String passWord);
	public User findByUserName(String username);
}
