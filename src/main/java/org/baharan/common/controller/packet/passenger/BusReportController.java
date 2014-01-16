package org.baharan.common.controller.packet.passenger;

import org.baharan.common.viewmodel.packet.passenger.bus.BusWorkDailyViewModel;
import org.baharan.common.viewmodel.packet.passenger.bus.BusWorkDetailViewModel;
import org.baharan.common.viewmodel.packet.passenger.bus.BusWorkHourlyViewModel;
import org.baharan.common.viewmodel.packet.passenger.bus.BusWorkMonthlyViewModel;
import org.baharan.core.QueryResult;
import org.baharan.service.packet.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/packet/passenger/report/bus")
public class BusReportController {

	@Autowired
	private IPassengerService passengerService;
	
	@RequestMapping("/workDetail")
	@ResponseBody
	public  QueryResult<BusWorkDetailViewModel> BusWorkDetail(	String busCode, 
																	String fromDate, 
																	String toDate,
																	Integer locationId  ,
																	String order, int pageNumber, int pageSize) {
		return passengerService.BusWorkDetail(busCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}
	
	
	@RequestMapping("/workHourly")
	@ResponseBody
	public  QueryResult<BusWorkHourlyViewModel> BusWorkHourly(	String busCode, 
																	String date, 
																	String fromHour,
																	String toHour,
																	Integer locationId ,
																	String order, int pageNumber, int pageSize) {
		return passengerService.BusWorkHourly(busCode, date, fromHour, toHour, locationId, order, pageNumber, pageSize);
	}
	
	
	@RequestMapping("/workDaily")
	@ResponseBody
	public  QueryResult<BusWorkDailyViewModel> BusWorkDaily(	String busCode, 
																String fromDate, 
																String toDate,
																Integer locationId ,
																String order, int pageNumber, int pageSize){
		return passengerService.BusWorkDaily(busCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/workMonthly")
	@ResponseBody
	public  QueryResult<BusWorkMonthlyViewModel> BusWorkMonthly(	String busCode, 
			String year, 
																	String month,
																	Integer locationId,
																	String order, int pageNumber, int pageSize) {
		return passengerService.BusWorkMonthly(busCode, year, month, locationId, order, pageNumber, pageSize);
	}
	
	
}
