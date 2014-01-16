package org.baharan.common.controller.baseInfo;

import java.util.Date;
import org.baharan.core.QueryResult;
import org.baharan.dao.baseInfo.IBaseInformationRepository;
import org.baharan.model.baseInfo.BaseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/baseinformation")
public class BaseInformationController 
{
	@Autowired(required = true)
	private IBaseInformationRepository baseRepo;
	

	@RequestMapping("/list/{parentId}")
	@ResponseBody
	public  QueryResult<BaseInformation> listById(@PathVariable int parentId, String searchFilter, String order, Integer pageNumber, Integer pageSize) {
		return baseRepo.getAllGrid(parentId, searchFilter, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/load/{Id}")
	@ResponseBody
	public  BaseInformation load(@PathVariable int Id) {
		return baseRepo.loadByEntityId(Id);	
	}
	
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public  Boolean delete(@PathVariable int Id) {
		baseRepo.deleteByEntityId(Id);
		return true;
	}
	
	
	@RequestMapping(value = "/save/{parentId}", method = RequestMethod.POST)
	@ResponseBody
	public  Boolean save(@RequestBody BaseInformation base, @PathVariable int parentId) 
	{
		if (base.getId() > 0) {
			base.setUpdatedDate(new Date());
			baseRepo.update(base);
		} else {
			BaseInformation parent = new BaseInformation();
			parent.setId(parentId);
			base.setParent(parent);
			base.setCreatedDate(new Date());
			base.setUpdatedDate(new Date());
			baseRepo.add(base);
		}
		return true;
	}
 
}


