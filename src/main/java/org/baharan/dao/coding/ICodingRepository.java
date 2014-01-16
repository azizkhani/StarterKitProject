package org.baharan.dao.coding;

import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.baharan.model.coding.CodingTitle;

public interface ICodingRepository extends  IGenericRepository<CodingTitle>{
	public List<CodingTitle> findByCodingId(int codingId);
}
