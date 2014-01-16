package org.baharan.dao.baseInfo;


import org.baharan.core.QueryResult;
import org.baharan.dao.IGenericRepository;
import org.baharan.model.baseInfo.BaseInformation;

public interface IBaseInformationRepository extends IGenericRepository<BaseInformation>{
	
	public QueryResult<BaseInformation> getAllGrid(int parentId, String where, String order, int pageNumber,int pageSize);
}
