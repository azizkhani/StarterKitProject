package org.baharan.service.imp.packet;

import java.util.Date;

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
import org.baharan.dao.packet.IPassengerRepository;
import org.baharan.model.line.LineStation;
import org.baharan.model.packet.Passenger;
import org.baharan.service.imp.GenericService;
import org.baharan.service.packet.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService  extends GenericService<Passenger> implements IPassengerService {

	
	@Autowired(required = true)
	private IPassengerRepository passengerRepo;
	
	@Override
	protected IGenericRepository<Passenger> getGenericRepo() {
		return passengerRepo;
	}
	
	@Override
	public void save(Passenger entity) {
		if (entity.getId() > 0) {
			entity.setUpdatedDate(new Date());
			this.update(entity);
		} else {
			entity.setCreatedDate(new Date());
			entity.setUpdatedDate(new Date());
			this.add(entity);
		}
	}

	@Override
	public QueryResult<LineWorkDetailViewModel> LineWorkDetail(String lineCode,
			String fromDate, String toDate, Integer locationId, String order,
			int pageNumber, int pageSize) {
		return passengerRepo.LineWorkDetail(lineCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<LineWorkHourlyViewModel> LineWorkHourly(String lineCode,
			String date, String fromHour, String toHour, Integer locationId,
			String order, int pageNumber, int pageSize) {
		return passengerRepo.LineWorkHourly(lineCode, date, fromHour, toHour, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<LineWorkDailyViewModel> LineWorkDaily(String lineCode,
			String fromDate, String toDate, Integer locationId, String order,
			int pageNumber, int pageSize) {
		return passengerRepo.LineWorkDaily(lineCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<LineWorkMonthlyViewModel> LineWorkMonthly(
			String lineCode, String year, String month, Integer locationId,
			String order, int pageNumber, int pageSize) {
		return passengerRepo.LineWorkMonthly(lineCode, year, month, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<BusWorkDetailViewModel> BusWorkDetail(String busCode,
			String fromDate, String toDate, Integer locationId, String order,
			int pageNumber, int pageSize) {
		return passengerRepo.BusWorkDetail(busCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<BusWorkHourlyViewModel> BusWorkHourly(String busCode,
			String date, String fromHour, String toHour, Integer locationId,
			String order, int pageNumber, int pageSize) {
		return passengerRepo.BusWorkHourly(busCode, date, fromHour, toHour, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<BusWorkDailyViewModel> BusWorkDaily(String busCode,
			String fromDate, String toDate, Integer locationId, String order,
			int pageNumber, int pageSize) {
		return passengerRepo.BusWorkDaily(busCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<BusWorkMonthlyViewModel> BusWorkMonthly(String busCode,
			String year, String month, Integer locationId, String order,
			int pageNumber, int pageSize) {
		return passengerRepo.BusWorkMonthly(busCode, year, month, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<DriverWorkDetailViewModel> DriverWorkDetail(
			String driverCode, String fromDate, String toDate,
			Integer locationId, String order, int pageNumber, int pageSize) {
		return passengerRepo.DriverWorkDetail(driverCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<DriverWorkHourlyViewModel> DriverWorkHourly(
			String driverCode, String date, String fromHour, String toHour,
			Integer locationId, String order, int pageNumber, int pageSize) {
		return passengerRepo.DriverWorkHourly(driverCode, date, fromHour, toHour, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<DriverWorkDailyViewModel> DriverWorkDaily(
			String driverCode, String fromDate, String toDate,
			Integer locationId, String order, int pageNumber, int pageSize) {
		return passengerRepo.DriverWorkDaily(driverCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<DriverWorkMonthlyViewModel> DriverWorkMonthly(
			String driverCode, String year, String month, Integer locationId,
			String order, int pageNumber, int pageSize) {
		return passengerRepo.DriverWorkMonthly(driverCode, year, month, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<DeviceWorkDetailViewModel> DeviceWorkDetail(
			Integer DeviceGroupId, String deviceCode, String fromDate,
			String toDate, Integer locationId, String order, int pageNumber,
			int pageSize) {
		return passengerRepo.DeviceWorkDetail(DeviceGroupId, deviceCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<DeviceWorkHourlyViewModel> DeviceWorkHourly(
			Integer DeviceGroupId, String deviceCode, String date,
			String fromHour, String toHour, Integer locationId, String order,
			int pageNumber, int pageSize) {
		return passengerRepo.DeviceWorkHourly(DeviceGroupId, deviceCode, date, fromHour, toHour, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<DeviceWorkDailyViewModel> DeviceWorkDaily(
			Integer DeviceGroupId, String deviceCode, String fromDate,
			String toDate, Integer locationId, String order, int pageNumber,
			int pageSize) {
		return passengerRepo.DeviceWorkDaily(DeviceGroupId, deviceCode, fromDate, toDate, locationId, order, pageNumber, pageSize);
	}

	@Override
	public QueryResult<DeviceWorkMonthlyViewModel> DeviceWorkMonthly(
			Integer DeviceGroupId, String deviceCode, String year,
			String month, Integer locationId, String order, int pageNumber,
			int pageSize) {
		return passengerRepo.DeviceWorkMonthly(DeviceGroupId, deviceCode, year, month, locationId, order, pageNumber, pageSize);
	}

	
}