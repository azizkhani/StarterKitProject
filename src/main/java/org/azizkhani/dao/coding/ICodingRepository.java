package org.azizkhani.dao.coding;

import java.util.List;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.coding.CodingTitle;

public interface ICodingRepository extends  IGenericRepository<CodingTitle>{
	public List<CodingTitle> findByCodingId(int codingId);
}
