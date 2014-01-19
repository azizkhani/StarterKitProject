package org.azizkhani.dao.impl.hibernate.security;


import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.dao.security.IGroupRepository;
import org.azizkhani.model.security.Group;
import org.springframework.stereotype.Repository;


@Repository
public class GroupRepository extends GenericRepository<Group> implements IGroupRepository {

	@Override
	protected Class<Group> getDomainClass() {
		return Group.class;
	}
}
