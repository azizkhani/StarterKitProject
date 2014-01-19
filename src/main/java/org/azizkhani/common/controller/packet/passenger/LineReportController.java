package org.azizkhani.common.controller.packet.passenger;

import org.azizkhani.common.viewmodel.packet.passenger.line.LineWorkDailyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.line.LineWorkDetailViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.line.LineWorkHourlyViewModel;
import org.azizkhani.common.viewmodel.packet.passenger.line.LineWorkMonthlyViewModel;
import org.azizkhani.core.QueryResult;
import org.azizkhani.model.packet.Passenger;
import org.azizkhani.service.packet.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/packet/passenger/report/line")
public class LineReportController {

	@Autowired
	private IPassengerService passengerService;
	
	@RequestMapping("/workDetail")
	@ResponseBody
	public  QueryResult<LineWorkDetailViewModel> LineWorkDetail(	String lineCode, 
																	String fromDate, 
																	String toDate,
																	Integer locationId  ,
																	String order, int pageNumber, int pageSize) {
		return passengerService.LineWorkDetail(lineCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}
	
	
	@RequestMapping("/workHourly")
	@ResponseBody
	public  QueryResult<LineWorkHourlyViewModel> LineWorkHourly(	String lineCode, 
																	String date, 
																	String fromHour,
																	String toHour,
																	Integer locationId ,
																	String order, int pageNumber, int pageSize) {
		return passengerService.LineWorkHourly(lineCode, date, fromHour, toHour, locationId, order, pageNumber, pageSize);
	}
	
	
	@RequestMapping("/workDaily")
	@ResponseBody
	public  QueryResult<LineWorkDailyViewModel> LineWorkDaily(	String lineCode, 
																String fromDate, 
																String toDate,
																Integer locationId ,
																String order, int pageNumber, int pageSize){
		return passengerService.LineWorkDaily(lineCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/workMonthly")
	@ResponseBody
	public  QueryResult<LineWorkMonthlyViewModel> LineWorkMonthly(	String lineCode, 
			String year, 
																	String month,
																	Integer locationId,
																	String order, int pageNumber, int pageSize) {
		return passengerService.LineWorkMonthly(lineCode, year, month, locationId, order, pageNumber, pageSize);
	}
	
}
