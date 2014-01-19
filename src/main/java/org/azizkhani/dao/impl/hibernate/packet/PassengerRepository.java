package org.azizkhani.dao.impl.hibernate.packet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.azizkhani.common.utility.HQLUtility;
import org.azizkhani.common.viewmodel.packet.passenger.bus.BusWorkDailyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.bus.BusWorkDetailViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.bus.BusWorkHourlyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.bus.BusWorkMonthlyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.device.DeviceWorkDailyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.device.DeviceWorkDetailViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.device.DeviceWorkHourlyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.device.DeviceWorkMonthlyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.driver.DriverWorkDailyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.driver.DriverWorkDetailViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.driver.DriverWorkHourlyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.driver.DriverWorkMonthlyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.line.LineWorkDailyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.line.LineWorkDetailViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.line.LineWorkHourlyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.line.LineWorkMonthlyViewModel;
import org.azizkhani.core.QueryResult;
import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.dao.packet.IPassengerRepository;
import org.azizkhani.model.packet.Passenger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerRepository extends GenericRepository<Passenger> implements IPassengerRepository {

	@Override
	protected Class<Passenger> getDomainClass() {
		return Passenger.class;
	}
	public static void setParamsToQuery(HashMap hm,Query query){
		Set set=hm.entrySet();
		Iterator itr=set.iterator(); 
		while(itr.hasNext()){
			Map.Entry entry=(Map.Entry) itr.next();
			if(entry.getValue() instanceof Integer)
				query.setInteger(entry.getKey().toString(), (Integer)entry.getValue());
			else if(entry.getValue() instanceof Long)
				query.setLong(entry.getKey().toString(), (Long)entry.getValue());
			else
				query.setParameter(entry.getKey().toString(), entry.getValue());
		}
	}
	////////////////////////////////////line////////////////////////////////////////////////////////////
	@Override
	public QueryResult<LineWorkDetailViewModel> LineWorkDetail(String lineCode,
			String fromDate, String toDate, Integer locationId, String order,
			int pageNumber, int pageSize) {
		
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(lineCode.length()>0){
			jql.append("and  e.line.code=:lineCode" );
			params.put("lineCode", lineCode);
		}
		
		if(fromDate.length()>0 && toDate.length()>0){
			jql.append("and  e.actionDate between :fromDate and :toDate" );
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		else if(fromDate.length()>0){
			jql.append("and  e.actionDate>=:fromDate" );
			params.put("fromDate", fromDate);
		}
		else if(toDate.length()>0){
			jql.append("and  e.actionDate<=:toDate" );
			params.put("toDate", toDate);
		}
		
		Query query = session.createQuery("select e.line.code as lineCode," +
												" e.line.name as lineName," +
												" e.actionDate as actionDate," +
												" e.actionTime as actionTime," +
												" e.amount as amount "+jql.toString());
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(LineWorkDetailViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<LineWorkDetailViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<LineWorkDetailViewModel>(pageNumber, count, pageSize,list);
		
	}

	@Override
	public QueryResult<LineWorkHourlyViewModel> LineWorkHourly(String lineCode,
			String date, String fromHour, String toHour, Integer locationId,
			String order, int pageNumber, int pageSize) {
		
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(lineCode.length()>0){
			jql.append("and  e.line.code=:lineCode" );
			params.put("lineCode", lineCode);
		}
		
		if(fromHour.length()>0 && toHour.length()>0){
			jql.append("and  substring(e.actionTime,1,2) between :fromHour and :toHour" );
			params.put("fromHour", fromHour);
			params.put("toHour", toHour);
		}
		else if(fromHour.length()>0){
			jql.append("and substring(e.actionTime,1,2) >=:fromHour" );
			params.put("fromHour", fromHour);
		}
		else if(toHour.length()>0){
			jql.append("and  substring(e.actionTime,1,2) <=:toHour" );
			params.put("toHour", toHour);
		}
		
		Query query = session.createQuery("select e.line.code as lineCode," +
												" e.line.name as lineName," +
												" substring (  e.actionTime ,1,2 ) as houre," +
												" count(*) as count," +
												" sum(e.amount) as amount "+jql.toString()+
												" group by e.line.code,e.line.name,substring(e.actionTime,1,2)");
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(LineWorkHourlyViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<LineWorkHourlyViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<LineWorkHourlyViewModel>(pageNumber, count, pageSize,list);
		
		
	}

	@Override
	public QueryResult<LineWorkDailyViewModel> LineWorkDaily(String lineCode,
			String fromDate, String toDate, Integer locationId, String order,
			int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(lineCode.length()>0){
			jql.append("and  e.line.code=:lineCode" );
			params.put("lineCode", lineCode);
		}
		
		if(fromDate.length()>0 && toDate.length()>0){
			jql.append("and  e.actionDate between :fromDate and :toDate" );
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		else if(fromDate.length()>0){
			jql.append("and  e.actionDate>=:fromDate" );
			params.put("fromDate", fromDate);
		}
		else if(toDate.length()>0){
			jql.append("and  e.actionDate<=:toDate" );
			params.put("toDate", toDate);
		}
		
		Query query = session.createQuery("select e.line.code as lineCode," +
												" e.line.name as lineName," +
												" e.actionDate as date," +
												" count(*) as count," +
												" sum(e.amount) as amount " +jql.toString()+
												" group by e.line.code,e.line.name,e.actionDate");
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(LineWorkDailyViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<LineWorkDailyViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<LineWorkDailyViewModel>(pageNumber, count, pageSize,list);
	}

	@Override
	public QueryResult<LineWorkMonthlyViewModel> LineWorkMonthly(
			String lineCode, String year, String month, Integer locationId,
			String order, int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(lineCode.length()>0){
			jql.append("and  e.line.code=:lineCode" );
			params.put("lineCode", lineCode);
		}
		
		if(year.length()>0){
			jql.append("and substring(e.actionDate,1,4)=:year" );
			params.put("year", year);
		}
		else if(month.length()>0 ){
			jql.append("and  substring(e.actionDate,6,2)=:month" );
			params.put("month", month);
		}
		
		Query query = session.createQuery("select e.line.code as lineCode," +
												" e.line.name as lineName," +
												" substring (  e.actionDate ,6,2 ) as month," +
												" count(*) as count," +
												" sum(e.amount) as amount "+jql.toString()+
												" group by e.line.code,e.line.name,substring(e.actionDate,6,2)");
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(LineWorkMonthlyViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<LineWorkMonthlyViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<LineWorkMonthlyViewModel>(pageNumber, count, pageSize,list);
	}
	
	
	////////////////////////////////////bus////////////////////////////////////////////////////////////
	@Override
	public QueryResult<BusWorkDetailViewModel> BusWorkDetail(String busCode,
			String fromDate, String toDate, Integer locationId, String order,
			int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(busCode.length()>0){
			jql.append("and  e.bus.code=:busCode" );
			params.put("busCode", busCode);
		}
		
		if(fromDate.length()>0 && toDate.length()>0){
			jql.append("and  e.actionDate between :fromDate and :toDate" );
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		else if(fromDate.length()>0){
			jql.append("and  e.actionDate>=:fromDate" );
			params.put("fromDate", fromDate);
		}
		else if(toDate.length()>0){
			jql.append("and  e.actionDate<=:toDate" );
			params.put("toDate", toDate);
		}
		
		Query query = session.createQuery("select e.bus.code as vehicleCode," +
												" (e.bus.plaque1 || e.bus.plaque2 || e.bus.plaque3 || e.bus.plaque4) as vehiclePlaque," +
												" e.driver.firstName as personFirstName," +
												" e.driver.lastName as personLastName," +
												" e.actionDate as passengerPackageActionDate," +
												" e.actionTime as passengerPackageActionTime," +
												" e.amount as passengerPackageAmount," +
												" e.cardSerial as  passengerPackageCardSerial " +jql.toString());
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(BusWorkDetailViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<BusWorkDetailViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<BusWorkDetailViewModel>(pageNumber, count, pageSize,list);
	}

	@Override
	public QueryResult<BusWorkHourlyViewModel> BusWorkHourly(String busCode,
			String date, String fromHour, String toHour, Integer locationId,
			String order, int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(busCode.length()>0){
			jql.append("and  e.line.code=:busCode" );
			params.put("busCode", busCode);
		}
		
		if(fromHour.length()>0 && toHour.length()>0){
			jql.append("and  substring(e.actionTime,1,2) between :fromHour and :toHour" );
			params.put("fromDate", fromHour);
			params.put("toHour", toHour);
		}
		else if(fromHour.length()>0){
			jql.append("and substring(e.actionTime,1,2) >=:fromHour" );
			params.put("fromHour", fromHour);
		}
		else if(toHour.length()>0){
			jql.append("and  substring(e.actionTime,1,2) <=:toHour" );
			params.put("toHour", toHour);
		}
		
		Query query = session.createQuery("select e.bus.code as passengerPackageBusCode," +
												" substring (  e.actionTime ,1,2 ) as hour," +
												" count(*) as count," +
												" sum(e.amount) as amount "+jql.toString()+
												" group by e.bus.code,substring(e.actionTime,1,2)");
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(BusWorkHourlyViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<BusWorkHourlyViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<BusWorkHourlyViewModel>(pageNumber, count, pageSize,list);
	}

	@Override
	public QueryResult<BusWorkDailyViewModel> BusWorkDaily(String busCode,
			String fromDate, String toDate, Integer locationId, String order,
			int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(busCode.length()>0){
			jql.append("and  e.bus.code=:busCode" );
			params.put("busCode", busCode);
		}
		
		if(fromDate.length()>0 && toDate.length()>0){
			jql.append("and  e.actionDate between :fromDate and :toDate" );
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		else if(fromDate.length()>0){
			jql.append("and  e.actionDate>=:fromDate" );
			params.put("fromDate", fromDate);
		}
		else if(toDate.length()>0){
			jql.append("and  e.actionDate<=:toDate" );
			params.put("toDate", toDate);
		}
		
		Query query = session.createQuery("select e.bus.code as passengerPackageBusCode," +
												" e.actionDate as passengerPackageActionDate," +
												" count(*) as count," +
												" sum(e.amount) as amount " +jql.toString()+
												" group by e.bus.code,e.actionDate");
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(BusWorkDailyViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<BusWorkDailyViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<BusWorkDailyViewModel>(pageNumber, count, pageSize,list);
	}

	@Override
	public QueryResult<BusWorkMonthlyViewModel> BusWorkMonthly(String busCode,
			String year, String month, Integer locationId, String order,
			int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(busCode.length()>0){
			jql.append("and  e.bus.code=:busCode" );
			params.put("busCode", busCode);
		}
		
		if(year.length()>0){
			jql.append("and substring(e.actionDate,1,4)=:year" );
			params.put("year", year);
		}
		else if(month.length()>0){
			jql.append("and  substring(e.actionDate,6,2)=:month" );
			params.put("month", month);
		}
		
		Query query = session.createQuery("select e.bus.code as passengerPackageBusCode," +
												" substring (  e.actionDate ,6,2 ) as month," +
												" count(*) as count," +
												" sum(e.amount) as amount "+jql.toString()+
												" group by e.bus.code,substring(e.actionDate,6,2)");
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(BusWorkMonthlyViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<BusWorkMonthlyViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<BusWorkMonthlyViewModel>(pageNumber, count, pageSize,list);
	}

	
	////////////////////////////////////driver////////////////////////////////////////////////////////////
	@Override
	public QueryResult<DriverWorkDetailViewModel> DriverWorkDetail(
			String driverCode, String fromDate, String toDate,
			Integer locationId, String order, int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(driverCode.length()>0){
			jql.append("and  e.driver.personCode=:driverCode" );
			params.put("driverCode", driverCode);
		}
		
		if(fromDate.length()>0 && toDate.length()>0){
			jql.append("and  e.actionDate between :fromDate and :toDate" );
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		else if(fromDate.length()>0){
			jql.append("and  e.actionDate>=:fromDate" );
			params.put("fromDate", fromDate);
		}
		else if(toDate.length()>0){
			jql.append("and  e.actionDate<=:toDate" );
			params.put("toDate", toDate);
		}
		
		Query query = session.createQuery("select e.driver.personCode as personCode," +
												" (e.bus.plaque1 || e.bus.plaque2 || e.bus.plaque3 || e.bus.plaque4) as vehiclePlaque," +
												" e.driver.firstName as personFirstName," +
												" e.driver.lastName as personLastName," +
												" e.actionDate as passengerPackageActionDate," +
												" e.actionTime as passengerPackageActionTime," +
												" e.amount as passengerPackageAmount "  +jql.toString());
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(DriverWorkDetailViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<DriverWorkDetailViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<DriverWorkDetailViewModel>(pageNumber, count, pageSize,list);
	}

	@Override
	public QueryResult<DriverWorkHourlyViewModel> DriverWorkHourly(
			String driverCode, String date, String fromHour, String toHour,
			Integer locationId, String order, int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(driverCode.length()>0){
			jql.append("and  e.driver.personCode=:driverCode" );
			params.put("driverCode", driverCode);
		}
		
		if(fromHour.length()>0 && toHour.length()>0){
			jql.append("and  substring(e.actionTime,1,2) between :fromHour and :toHour" );
			params.put("fromDate", fromHour);
			params.put("toHour", toHour);
		}
		else if(fromHour.length()>0){
			jql.append("and substring(e.actionTime,1,2) >=:fromHour" );
			params.put("fromHour", fromHour);
		}
		else if(toHour.length()>0){
			jql.append("and  substring(e.actionTime,1,2) <=:toHour" );
			params.put("toHour", toHour);
		}
		
		Query query = session.createQuery("select e.driver.personCode as passengerPackageDriverCode," +
												" e.driver.firstName as personFirstName," +
												" e.driver.lastName as personLastName," +
												" substring (  e.actionTime ,1,2 ) as hour," +
												" count(*) as count," +
												" sum(e.amount) as amount "+jql.toString()+
												" group by e.driver.personCode,e.driver.firstName,e.driver.lastName,substring(e.actionTime,1,2)");
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(DriverWorkHourlyViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<DriverWorkHourlyViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<DriverWorkHourlyViewModel>(pageNumber, count, pageSize,list);
	}

	@Override
	public QueryResult<DriverWorkDailyViewModel> DriverWorkDaily(
			String driverCode, String fromDate, String toDate,
			Integer locationId, String order, int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(driverCode.length()>0){
			jql.append("and  e.driver.personCode=:driverCode" );
			params.put("driverCode", driverCode);
		}
		
		if(fromDate.length()>0 && toDate.length()>0){
			jql.append("and  e.actionDate between :fromDate and :toDate" );
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		else if(fromDate.length()>0){
			jql.append("and  e.actionDate>=:fromDate" );
			params.put("fromDate", fromDate);
		}
		else if(toDate.length()>0){
			jql.append("and  e.actionDate<=:toDate" );
			params.put("toDate", toDate);
		}
		
		Query query = session.createQuery("select e.driver.personCode as passengerPackageDriverCode," +
												" e.driver.firstName as personFirstName," +
												" e.driver.lastName as personLastName," +
												" e.actionDate as passengerPackageActionDate," +
												" count(*) as count," +
												" sum(e.amount) as amount " +jql.toString()+
												" group by e.driver.personCode,e.driver.firstName,e.driver.lastName,e.actionDate");
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(DriverWorkDailyViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<DriverWorkDailyViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<DriverWorkDailyViewModel>(pageNumber, count, pageSize,list);
	}

	@Override
	public QueryResult<DriverWorkMonthlyViewModel> DriverWorkMonthly(
			String driverCode, String year, String month, Integer locationId,
			String order, int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2 ");
		HQLUtility.toHQL(jql, "", order);
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		if(locationId!=null && locationId>0){
			jql.append("and  e.location.id=:locationId" );
			params.put("locationId", locationId);
		}
		if(driverCode.length()>0){
			jql.append("and  e.driver.personCode=:driverCode" );
			params.put("driverCode", driverCode);
		}
		
		if(year.length()>0){
			jql.append("and substring(e.actionDate,1,4)=:year" );
			params.put("year", year);
		}
		else if(month.length()>0){
			jql.append("and  substring(e.actionDate,6,2)=:month" );
			params.put("month", month);
		}
		
		Query query = session.createQuery("select e.driver.personCode as passengerPackageDriverCode," +
												" e.driver.firstName as personFirstName," +
												" e.driver.lastName as personLastName," +
												" substring (  e.actionDate ,6,2 ) as month," +
												" count(*) as count," +
												" sum(e.amount) as amount "+jql.toString()+
												" group by e.driver.personCode,e.driver.firstName,e.driver.lastName,substring(e.actionDate,6,2)");
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(DriverWorkMonthlyViewModel.class));
		//set params to query???????????????????????????????????????????
		setParamsToQuery(params,query);
		
		
		List<DriverWorkMonthlyViewModel> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  "+jql.toString());
		HQLUtility.toHQL(jqlCount, "", "");
		Query createQuery = getSession().createQuery(jqlCount.toString());
		setParamsToQuery(params,createQuery);
		int count=((Long)createQuery.uniqueResult()).intValue();
		
		return  new QueryResult<DriverWorkMonthlyViewModel>(pageNumber, count, pageSize,list);
	}
	
	////////////////////////////////////device////////////////////////////////////////////////////////////
	@Override
	public QueryResult<DeviceWorkDetailViewModel> DeviceWorkDetail(
			Integer DeviceGroupId, String deviceCode, String fromDate,
			String toDate, Integer locationId, String order, int pageNumber,
			int pageSize) {
		return null;
	}

	@Override
	public QueryResult<DeviceWorkHourlyViewModel> DeviceWorkHourly(
			Integer DeviceGroupId, String deviceCode, String date,
			String fromHour, String toHour, Integer locationId, String order,
			int pageNumber, int pageSize) {
		return null;
	}

	@Override
	public QueryResult<DeviceWorkDailyViewModel> DeviceWorkDaily(
			Integer DeviceGroupId, String deviceCode, String fromDate,
			String toDate, Integer locationId, String order, int pageNumber,
			int pageSize) {
		return null;
	}

	@Override
	public QueryResult<DeviceWorkMonthlyViewModel> DeviceWorkMonthly(
			Integer DeviceGroupId, String deviceCode, String year,
			String month, Integer locationId, String order, int pageNumber,
			int pageSize) {
		return null;
	}

	
	
}
