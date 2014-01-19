package org.azizkhani.dao.impl.hibernate.vehicle;

import java.util.List;

import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.dao.vehicle.IVehicleRepository;
import org.azizkhani.model.device.DeviceHistory;
import org.azizkhani.model.line.Line;
import org.azizkhani.model.vehicle.Vehicle;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class VehicleRepository extends GenericRepository<Vehicle> implements IVehicleRepository {

	@Override
	protected Class<Vehicle> getDomainClass() {
		return Vehicle.class;
	}
	
	@Override
	public List<DeviceHistory> getDevices(int vehicleId) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("select e from DeviceHistory e  where e.status=true and  e.vehicle.id=:vehicleId ");
		Query query = session.createQuery(jql.toString());
		query.setInteger("vehicleId", vehicleId);
		return  query.list();
	}

	@Override
	public Boolean removeLine(int vehicleId, int lineId) {
		Session session = getSession();
		Vehicle vehicle = (Vehicle) session.load(Vehicle.class, vehicleId);
		vehicle.getLines().remove(new Line(lineId));
		session.update(vehicle);
		session.flush(); 
		return true;
	}

	@Override
	public Boolean addLine(int vehicleId, int lineId) {
		Session session = getSession();
		Vehicle vehicle = (Vehicle) session.load(Vehicle.class, vehicleId);
		vehicle.getLines().add(new Line(lineId));
		session.update(vehicle);
		session.flush(); 
		return true;
	}
}
