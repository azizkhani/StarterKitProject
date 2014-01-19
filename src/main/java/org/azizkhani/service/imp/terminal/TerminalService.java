package org.azizkhani.service.imp.terminal;

import java.util.Date;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.dao.terminal.ITerminalRepository;
import org.azizkhani.model.line.LineStation;
import org.azizkhani.model.terminal.Terminal;
import org.azizkhani.service.imp.GenericService;
import org.azizkhani.service.terminal.ITerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TerminalService extends GenericService<Terminal> implements ITerminalService {

	
	@Autowired(required = true)
	private ITerminalRepository terminalRepo;
	
	@Override
	protected IGenericRepository<Terminal> getGenericRepo() {
		return terminalRepo;
	}
	
	@Override
	public void save(Terminal entity) {
		if (entity.getId() > 0) {
			entity.setUpdatedDate(new Date());
			this.update(entity);
		} else {
			entity.setCreatedDate(new Date());
			entity.setUpdatedDate(new Date());
			this.add(entity);
		}
	}

}
