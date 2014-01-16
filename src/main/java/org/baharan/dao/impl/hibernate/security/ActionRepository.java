package org.baharan.dao.impl.hibernate.security;

import org.baharan.dao.security.IActionRepository;
import org.baharan.dao.security.IUserRepository;
import org.baharan.dao.impl.hibernate.GenericRepository;
import org.baharan.model.security.Action;

public class ActionRepository extends GenericRepository<Action> implements IActionRepository {

	@Override
	protected Class<Action> getDomainClass() {
		return Action.class;
	}
}
