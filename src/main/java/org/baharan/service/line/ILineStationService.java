package org.baharan.service.line;

import java.util.List;

import org.baharan.model.line.LineStation;
import org.baharan.service.IGenericService;

public interface ILineStationService extends IGenericService<LineStation> {
	public List<LineStation> getAll(int lineId);
}
