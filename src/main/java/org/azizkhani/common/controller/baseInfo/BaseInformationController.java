package org.azizkhani.common.controller.baseInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.azizkhani.common.utility.dozer.annotation.RequestView;
import org.azizkhani.common.utility.dozer.annotation.ResponseView;
import org.azizkhani.common.viewmodel.BaseInformationViewModel;
import org.azizkhani.core.QueryResult;
import org.azizkhani.dao.baseInfo.IBaseInformationRepository;
import org.azizkhani.model.baseInfo.BaseInformation;
import org.azizkhani.service.cartable.ICartableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public QueryResult<BaseInformation> listById(@PathVariable int parentId, String searchFilter, String order, Integer pageNumber, Integer pageSize) {
		return baseRepo.getAllGrid(parentId, searchFilter, order, pageNumber, pageSize);
	}

	@RequestMapping("/load/{Id}")
	@ResponseBody
	@ResponseView(BaseInformationViewModel.class)
	public BaseInformation load(@PathVariable int Id) {
		return baseRepo.loadByEntityId(Id);
	}

	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean delete(@PathVariable int Id) {
		baseRepo.deleteByEntityId(Id);
		return true;
	}

	@RequestMapping(value = "/save/{parentId}", method = RequestMethod.POST)
	@ResponseBody
	@RequestView(BaseInformationViewModel.class)
	public Boolean save(@RequestBody BaseInformation base, @PathVariable int parentId) {
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
