package org.azizkhani.service.line;

import java.util.List;

import org.azizkhani.model.line.LineStation;
import org.azizkhani.service.IGenericService;

public interface ILineStationService extends IGenericService<LineStation> {
	public List<LineStation> getAll(int lineId);
}
