package org.baharan.dao.impl.hibernate.vehicle;

import java.util.List;

import org.baharan.dao.impl.hibernate.GenericRepository;
import org.baharan.dao.vehicle.IVehicleDriverRepository;
import org.baharan.model.device.Device;
import org.baharan.model.device.DeviceHistory;
import org.baharan.model.vehicle.VehicleDriver;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class VehicleDriverRepository extends GenericRepository<VehicleDriver> implements IVehicleDriverRepository {

	@Override
	protected Class<VehicleDriver> getDomainClass() {
		return VehicleDriver.class;
	}

	@Override
	public List<VehicleDriver> getAll(int vehicleId) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("select e from "+domainClass.getName()+" e  where e.vehicle.id=:vehicleId");
		Query query = session.createQuery(jql.toString());
		query.setInteger("vehicleId", vehicleId);
		return  query.list();
	}
}