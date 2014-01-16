package org.baharan.service.imp.station;

import java.util.Date;

import org.baharan.dao.IGenericRepository;
import org.baharan.dao.station.IStationRepository;
import org.baharan.model.line.LineStation;
import org.baharan.model.station.Station;
import org.baharan.service.imp.GenericService;
import org.baharan.service.station.IStationService;
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