package org.baharan.dao.organization;

import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.baharan.model.organization.*;

public interface IOrganizationStructureRepository extends IGenericRepository<OrganizationStructure> {
	
	public List<OrganizationStructure> getAll(String   parentId);
	public OrganizationStructure loadByEntityId(long entityId);
}
