package org.azizkhani.dao.organization;

import java.util.List;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.organization.*;

public interface IOrganizationStructureRepository extends IGenericRepository<OrganizationStructure> {
	
	public List<OrganizationStructure> getAll(String   parentId);
	public OrganizationStructure loadByEntityId(long entityId);
}
