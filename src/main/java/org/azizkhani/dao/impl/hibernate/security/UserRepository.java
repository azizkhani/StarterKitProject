package org.azizkhani.dao.impl.hibernate.security;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.dao.security.IUserRepository;
import org.azizkhani.model.organization.OrganizationStructure;
import org.azizkhani.model.security.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository extends GenericRepository<User> implements IUserRepository {
	private static final Logger logger = Logger.getLogger(UserRepository.class);
	
	@Override
	public Boolean checkUserNameExistance(String userName) {
		Query query=getSession().createQuery("select count(*) from User usr Where usr.userName=:userName");
		query.setParameter("userName", userName);
		List list = query.list();
		Long count = (Long) list.get(0);
		return count > 0 ? true : false;
	}

	@Override
	public User login(String userName, String passWord) {
		Query query=getSession().createQuery("from User usr Where usr.userName=:userName and usr.passWord=:passWord");
		query.setParameter("userName", userName);
		query.setParameter("passWord", passWord);
		List list = query.list();
		User user = (User) list.get(0);
		return user;
	}

	@Override
	@Transactional(readOnly = false)
	public User findByUserName(String userName) {
		Query query=getSession().createQuery("from User usr Where usr.userName=:userName ");
		query.setParameter("userName", userName);
		List list = query.list();
		if(list!=null && list.size()>0)
			return (User) list.get(0);
		return null;
	}
	@Override
	protected Class<User> getDomainClass() {
		return User.class;
	}
	

}
