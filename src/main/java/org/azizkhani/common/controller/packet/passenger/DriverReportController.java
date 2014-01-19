package org.azizkhani.common.controller.packet.passenger;

import org.azizkhani.common.viewmodel.packet.passenger.driver.DriverWorkDailyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.driver.DriverWorkDetailViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.driver.DriverWorkHourlyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.driver.DriverWorkMonthlyViewModel;
import org.azizkhani.core.QueryResult;
import org.azizkhani.service.packet.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/packet/passenger/report/driver")
public class DriverReportController {

	@Autowired
	private IPassengerService passengerService;

	@RequestMapping("/workDetail")
	@ResponseBody
	public  QueryResult<DriverWorkDetailViewModel> DriverWorkDetail(	String driverCode, 
																	String fromDate, 
																	String toDate,
																	Integer locationId  ,
																	String order, int pageNumber, int pageSize) {
		return passengerService.DriverWorkDetail(driverCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}
	
	
	@RequestMapping("/workHourly")
	@ResponseBody
	public  QueryResult<DriverWorkHourlyViewModel> DriverWorkHourly(	String driverCode, 
																	String date, 
																	String fromHour,
																	String toHour,
																	Integer locationId ,
																	String order, int pageNumber, int pageSize) {
		return passengerService.DriverWorkHourly(driverCode, date, fromHour, toHour, locationId, order, pageNumber, pageSize);
	}
	
	
	@RequestMapping("/workDaily")
	@ResponseBody
	public  QueryResult<DriverWorkDailyViewModel> DriverWorkDaily(	String driverCode, 
																String fromDate, 
																String toDate,
																Integer locationId ,
																String order, int pageNumber, int pageSize){
		return passengerService.DriverWorkDaily(driverCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/workMonthly")
	@ResponseBody
	public  QueryResult<DriverWorkMonthlyViewModel> DriverWorkMonthly(	String driverCode, 
			String year, 
																	String month,
																	Integer locationId,
																	String order, int pageNumber, int pageSize) {
		return passengerService.DriverWorkMonthly(driverCode, year, month, locationId, order, pageNumber, pageSize);
	}
}
