package org.azizkhani.service.imp.station;

import java.util.Date;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.dao.station.IStationRepository;
import org.azizkhani.model.line.LineStation;
import org.azizkhani.model.station.Station;
import org.azizkhani.service.imp.GenericService;
import org.azizkhani.service.station.IStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService extends GenericService<Station> implements IStationService {

	
	@Autowired(required = true)
	private IStationRepository stationRepo;
	
	@Override
	protected IGenericRepository<Station> getGenericRepo() {
		return stationRepo;
	}

	@Override
	public void save(Station entity) {
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