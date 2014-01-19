package org.azizkhani.dao.impl.hibernate.security;

import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.dao.security.IActionRepository;
import org.azizkhani.dao.security.IUserRepository;
import org.azizkhani.model.security.Action;
import org.springframework.stereotype.Repository;


@Repository
public class ActionRepository extends GenericRepository<Action> implements IActionRepository {

	@Override
	protected Class<Action> getDomainClass() {
		return Action.class;
	}
}
