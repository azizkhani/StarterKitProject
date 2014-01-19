package org.azizkhani.dao.impl.hibernate.terminal;

import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.dao.terminal.ITerminalRepository;
import org.azizkhani.model.terminal.Terminal;
import org.springframework.stereotype.Repository;


@Repository
public class TerminalRepository extends GenericRepository<Terminal> implements ITerminalRepository {

	@Override
	protected Class<Terminal> getDomainClass() {
		return Terminal.class;
	}
}
