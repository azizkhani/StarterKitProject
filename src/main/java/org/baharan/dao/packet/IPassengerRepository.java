package org.baharan.dao.packet;

import org.baharan.common.viewmodel.packet.passenger.bus.BusWorkDailyViewModel;
import org.baharan.common.viewmodel.packet.passenger.bus.BusWorkDetailViewModel;
import org.baharan.common.viewmodel.packet.passenger.bus.BusWorkHourlyViewModel;
import org.baharan.common.viewmodel.packet.passenger.bus.BusWorkMonthlyViewModel;
import org.baharan.common.viewmodel.packet.passenger.device.DeviceWorkDailyViewModel;
import org.baharan.common.viewmodel.packet.passenger.device.DeviceWorkDetailViewModel;
import org.baharan.common.viewmodel.packet.passenger.device.DeviceWorkHourlyViewModel;
import org.baharan.common.viewmodel.packet.passenger.device.DeviceWorkMonthlyViewModel;
import org.baharan.common.viewmodel.packet.passenger.driver.DriverWorkDailyViewModel;
import org.baharan.common.viewmodel.packet.passenger.driver.DriverWorkDetailViewModel;
import org.baharan.common.viewmodel.packet.passenger.driver.DriverWorkHourlyViewModel;
import org.baharan.common.viewmodel.packet.passenger.driver.DriverWorkMonthlyViewModel;
import org.baharan.common.viewmodel.packet.passenger.line.LineWorkDailyViewModel;
import org.baharan.common.viewmodel.packet.passenger.line.LineWorkDetailViewModel;
import org.baharan.common.viewmodel.packet.passenger.line.LineWorkHourlyViewModel;
import org.baharan.common.viewmodel.packet.passenger.line.LineWorkMonthlyViewModel;
import org.baharan.core.QueryResult;
import org.baharan.dao.IGenericRepository;
import org.baharan.model.packet.Passenger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface IPassengerRepository extends IGenericRepository<Passenger>{

	//------------------------Line-------------------------------------------------

	public  QueryResult<LineWorkDetailViewModel> LineWorkDetail(	String lineCode, 
													String fromDate, 
													String toDate,
													Integer locationId  ,
													String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<LineWorkHourlyViewModel> LineWorkHourly(	String lineCode, 
													String date, 
													String fromHour,
													String toHour,
													Integer locationId ,
													String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<LineWorkDailyViewModel> LineWorkDaily(	String lineCode, 
													String fromDate, 
													String toDate,
													Integer locationId ,
													String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<LineWorkMonthlyViewModel> LineWorkMonthly(	String lineCode, 
													String year, 
													String month,
													Integer locationId,
													String order, int pageNumber, int pageSize) ;
	
	//------------------------Bus-------------------------------------------------
	
	public  QueryResult<BusWorkDetailViewModel> BusWorkDetail(	String busCode, 
													String fromDate, 
													String toDate,
													Integer locationId  ,
													String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<BusWorkHourlyViewModel> BusWorkHourly(	String busCode, 
													String date, 
													String fromHour,
													String toHour,
													Integer locationId ,
													String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<BusWorkDailyViewModel> BusWorkDaily(	String busCode, 
													String fromDate, 
													String toDate,
													Integer locationId ,
													String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<BusWorkMonthlyViewModel> BusWorkMonthly(	String busCode, 
			String year, 
			String month,
													Integer locationId,
													String order, int pageNumber, int pageSize) ;
	
	
	//------------------------Driver-------------------------------------------------

	public  QueryResult<DriverWorkDetailViewModel> DriverWorkDetail(	String driverCode, 
														String fromDate, 
														String toDate,
														Integer locationId  ,
														String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<DriverWorkHourlyViewModel> DriverWorkHourly(	String driverCode, 
														String date, 
														String fromHour,
														String toHour,
														Integer locationId ,
														String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<DriverWorkDailyViewModel> DriverWorkDaily(		String driverCode, 
														String fromDate, 
														String toDate,
														Integer locationId ,
														String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<DriverWorkMonthlyViewModel> DriverWorkMonthly(	String driverCode, 
			String year, 
			String month,
														Integer locationId,
														String order, int pageNumber, int pageSize) ;
	
	//------------------------Device-------------------------------------------------

	public  QueryResult<DeviceWorkDetailViewModel> DeviceWorkDetail(	Integer DeviceGroupId,
														String deviceCode, 
														String fromDate, 
														String toDate,
														Integer locationId  ,
														String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<DeviceWorkHourlyViewModel> DeviceWorkHourly(	Integer DeviceGroupId,
														String deviceCode, 
														String date, 
														String fromHour,
														String toHour,
														Integer locationId ,
														String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<DeviceWorkDailyViewModel> DeviceWorkDaily(		Integer DeviceGroupId,
														String deviceCode, 
														String fromDate, 
														String toDate,
														Integer locationId ,
														String order, int pageNumber, int pageSize) ;
	
	public  QueryResult<DeviceWorkMonthlyViewModel> DeviceWorkMonthly(	Integer DeviceGroupId,
														String deviceCode, 
														String year, 
														String month,
														Integer locationId,
														String order, int pageNumber, int pageSize) ;
	
}
