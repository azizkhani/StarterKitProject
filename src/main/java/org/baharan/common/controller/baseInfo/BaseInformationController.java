package org.baharan.common.controller.baseInfo;

import java.util.Date;

import org.baharan.common.utility.dozer.annotation.CustomMapping;
import org.baharan.common.utility.dozer.annotation.CustomRequestBody;
import org.baharan.common.utility.dozer.annotation.ResponseView;
import org.baharan.common.viewmodel.BaseInformationViewModel;
import org.baharan.core.QueryResult;
import org.baharan.dao.baseInfo.IBaseInformationRepository;
import org.baharan.model.baseInfo.BaseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/baseinformation")
public class BaseInformationController {
	
	@Autowired(required = true)
	private IBaseInformationRepository baseRepo;
	

	@RequestMapping("/list/{parentId}")
	@ResponseBody
	public  QueryResult<BaseInformation> listById(@PathVariable int parentId, String searchFilter, String order, Integer pageNumber, Integer pageSize) {
		return baseRepo.getAllGrid(parentId, searchFilter, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/load/{Id}")
	@ResponseBody
	@ResponseView(BaseInformationViewModel.class)
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
	public  Boolean save( 	@CustomMapping(source=BaseInformationViewModel.class,destination=BaseInformation.class) 
							@CustomRequestBody 
							BaseInformation base, 
							@PathVariable int parentId) {
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


