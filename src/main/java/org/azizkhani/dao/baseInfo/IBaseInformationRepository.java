package org.azizkhani.dao.baseInfo;


import org.azizkhani.core.QueryResult;
import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.baseInfo.BaseInformation;

public interface IBaseInformationRepository extends IGenericRepository<BaseInformation>{
	
	public QueryResult<BaseInformation> getAllGrid(int parentId, String where, String order, int pageNumber,int pageSize);
}
