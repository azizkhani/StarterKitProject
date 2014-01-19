package org.azizkhani.common.controller.packet.passenger;

import org.azizkhani.common.viewmodel.packet.passenger.device.DeviceWorkDailyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.device.DeviceWorkDetailViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.device.DeviceWorkHourlyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.device.DeviceWorkMonthlyViewModel;
import org.azizkhani.core.QueryResult;
import org.azizkhani.service.packet.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/packet/passenger/report/device")
public class DeviceReportController {

	@Autowired
	private IPassengerService passengerService;
	
	@RequestMapping("/workDetail")
	@ResponseBody
	public  QueryResult<DeviceWorkDetailViewModel> DeviceWorkDetail(Integer deviceGroupId,	String deviceCode, 
																	String fromDate, 
																	String toDate,
																	Integer locationId  ,
																	String order, int pageNumber, int pageSize) {
		return passengerService.DeviceWorkDetail(deviceGroupId,deviceCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}
	
	
	@RequestMapping("/workHourly")
	@ResponseBody
	public  QueryResult<DeviceWorkHourlyViewModel> DeviceWorkHourly(Integer deviceGroupId,	
																	String deviceCode, 
																	String date, 
																	String fromHour,
																	String toHour,
																	Integer locationId ,
																	String order, int pageNumber, int pageSize) {
		return passengerService.DeviceWorkHourly(deviceGroupId,deviceCode, date, fromHour, toHour, locationId, order, pageNumber, pageSize);
	}
	
	
	@RequestMapping("/workDaily")
	@ResponseBody
	public  QueryResult<DeviceWorkDailyViewModel> DeviceWorkDaily(
																Integer deviceGroupId,	
																String deviceCode, 
																String fromDate, 
																String toDate,
																Integer locationId ,
																String order, int pageNumber, int pageSize){
		return passengerService.DeviceWorkDaily(deviceGroupId,deviceCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/workMonthly")
	@ResponseBody
	public  QueryResult<DeviceWorkMonthlyViewModel> DeviceWorkMonthly(
																	Integer deviceGroupId,	
																	String deviceCode, 
																	String year, 
																	String month,
																	Integer locationId,
																	String order, int pageNumber, int pageSize) {
		return passengerService.DeviceWorkMonthly(deviceGroupId,deviceCode, year, month, locationId, order, pageNumber, pageSize);
	}
	
	
}
