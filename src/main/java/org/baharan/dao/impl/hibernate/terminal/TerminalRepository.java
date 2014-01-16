package org.baharan.dao.impl.hibernate.terminal;

import org.baharan.dao.impl.hibernate.GenericRepository;
import org.baharan.dao.terminal.ITerminalRepository;
import org.baharan.model.terminal.Terminal;
import org.springframework.stereotype.Repository;


@Repository
public class TerminalRepository extends GenericRepository<Terminal> implements ITerminalRepository {

	@Override
	protected Class<Terminal> getDomainClass() {
		return Terminal.class;
	}
}
