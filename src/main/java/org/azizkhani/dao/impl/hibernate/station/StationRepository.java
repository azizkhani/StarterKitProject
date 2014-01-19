package org.azizkhani.dao.impl.hibernate.station;

import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.dao.station.IStationRepository;
import org.azizkhani.model.station.Station;
import org.springframework.stereotype.Repository;

@Repository
public class StationRepository extends GenericRepository<Station> implements IStationRepository {

	@Override
	protected Class<Station> getDomainClass() {
		return Station.class;
	}
}
