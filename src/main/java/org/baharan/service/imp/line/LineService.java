package org.baharan.service.imp.line;

import java.util.Date;

import org.baharan.dao.IGenericRepository;
import org.baharan.dao.line.ILineRepository;
import org.baharan.model.device.Device;
import org.baharan.model.line.Line;
import org.baharan.service.imp.GenericService;
import org.baharan.service.line.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LineService extends GenericService<Line> implements ILineService{
	
	@Autowired(required = true)
	private ILineRepository lineRepo;

	@Override
	protected IGenericRepository<Line> getGenericRepo() {
		return lineRepo;
	}
	
	@Override
	public void save(Line entity) {
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
