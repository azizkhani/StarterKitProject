package org.baharan.dao.impl.hibernate.device;
import java.util.List;
import org.baharan.dao.device.IDeviceGroupRepository;
import org.baharan.dao.impl.hibernate.GenericRepository;
import org.baharan.model.device.DeviceGroup;
import org.baharan.model.location.Location;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
@Repository
public class DeviceGroupRepository extends GenericRepository<DeviceGroup> implements IDeviceGroupRepository{
	
	@Override
	protected Class<DeviceGroup> getDomainClass() {
		return DeviceGroup.class;
	}
	
	@Override
	public List<DeviceGroup> getAll(int parentId) {
		Session session = getSession();		
		StringBuffer jql = new StringBuffer("from DeviceGroup e where    e.parent.id='"+parentId+"'");
		Query query = session.createQuery(jql.toString());
		return  query.list();
	}
	
	
	@Override
	public DeviceGroup findByCode(String code) {
		Session session = getSession();		
		StringBuffer jql = new StringBuffer("from DeviceGroup e where   e.code=:code");
		Query query = session.createQuery(jql.toString());
		query.setString("code", code);
		return  (DeviceGroup) query.uniqueResult();
	}
}
