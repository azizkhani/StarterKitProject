package org.azizkhani.dao.impl.hibernate.vehicle;

import java.util.List;

import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.dao.vehicle.IVehicleDriverRepository;
import org.azizkhani.model.device.Device;
import org.azizkhani.model.device.DeviceHistory;
import org.azizkhani.model.vehicle.VehicleDriver;
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