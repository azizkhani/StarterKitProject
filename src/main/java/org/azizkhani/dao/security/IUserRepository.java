package org.azizkhani.dao.security;


import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.security.User;



public interface IUserRepository extends  IGenericRepository<User>{
	public Boolean checkUserNameExistance(String userName);
	public User login(String userName,String passWord);
	public User findByUserName(String username);
}
