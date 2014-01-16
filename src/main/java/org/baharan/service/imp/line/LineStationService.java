package org.baharan.service.imp.line;

import java.util.Date;
import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.baharan.dao.line.ILineStationRepository;
import org.baharan.model.line.Line;
import org.baharan.model.line.LineStation;
import org.baharan.service.imp.GenericService;
import org.baharan.service.line.ILineStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class LineStationService extends GenericService<LineStation> implements ILineStationService{
	
	@Autowired(required = true)
	private ILineStationRepository lineStationRepo;

	@Override
	protected IGenericRepository<LineStation> getGenericRepo() {
		return lineStationRepo;
	}

	@Override
	public List<LineStation> getAll(int lineId) {
		return lineStationRepo.getAll(lineId);
	}
	
	
	@Override
	public void save(LineStation entity) {
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
