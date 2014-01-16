package org.baharan.dao.impl.hibernate.station;

import org.baharan.dao.impl.hibernate.GenericRepository;
import org.baharan.dao.station.IStationRepository;
import org.baharan.model.station.Station;
import org.springframework.stereotype.Repository;

@Repository
public class StationRepository extends GenericRepository<Station> implements IStationRepository {

	@Override
	protected Class<Station> getDomainClass() {
		return Station.class;
	}
}
