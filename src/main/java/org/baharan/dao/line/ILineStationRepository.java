package org.baharan.dao.line;

import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.baharan.model.line.Line;
import org.baharan.model.line.LineStation;

public interface ILineStationRepository extends IGenericRepository<LineStation> {
	public List<LineStation> getAll(int lineId);
}
