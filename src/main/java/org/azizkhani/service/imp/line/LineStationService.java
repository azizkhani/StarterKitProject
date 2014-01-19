package org.azizkhani.service.imp.line;

import java.util.Date;
import java.util.List;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.dao.line.ILineStationRepository;
import org.azizkhani.model.line.Line;
import org.azizkhani.model.line.LineStation;
import org.azizkhani.service.imp.GenericService;
import org.azizkhani.service.line.ILineStationService;
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
