package org.baharan.dao.impl.hibernate.security;


import org.baharan.dao.security.IGroupRepository;
import org.baharan.dao.impl.hibernate.GenericRepository;
import org.baharan.model.security.Group;
import org.springframework.stereotype.Repository;


@Repository
public class GroupRepository extends GenericRepository<Group> implements IGroupRepository {

	@Override
	protected Class<Group> getDomainClass() {
		return Group.class;
	}
}
