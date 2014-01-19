package org.azizkhani.dao.line;

import java.util.List;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.line.Line;
import org.azizkhani.model.line.LineStation;

public interface ILineStationRepository extends IGenericRepository<LineStation> {
	public List<LineStation> getAll(int lineId);
}
