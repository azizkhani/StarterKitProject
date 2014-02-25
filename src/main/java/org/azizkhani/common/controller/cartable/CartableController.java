package org.azizkhani.common.controller.cartable;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.azizkhani.common.utility.dozer.annotation.ResponseView;
import org.azizkhani.common.viewmodel.BaseInformationViewModel;
import org.azizkhani.common.viewmodel.cartable.ProcessDefinitionViewModel;
import org.azizkhani.common.viewmodel.cartable.TaskViewModel;
import org.azizkhani.model.baseInfo.BaseInformation;
import org.azizkhani.service.cartable.ICartableService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cartable")
public class CartableController {

	@Autowired
	private ICartableService cartableService;
	
	@Autowired(required = true)
	private Mapper dozerBeanMapper;
	
	
	@RequestMapping("/process/list")
	@ResponseBody
	public List<ProcessDefinitionViewModel> getAllProcess() {
		List<ProcessDefinition> procDef= cartableService.getProcessDefinitions();
		List<ProcessDefinitionViewModel> newList=new ArrayList<ProcessDefinitionViewModel>();
		for (ProcessDefinition processDefinition : procDef) {
			newList.add(dozerBeanMapper.map(processDefinition, ProcessDefinitionViewModel.class));
		}
		return newList;
	}
	
	@RequestMapping(value="/process/startById/",method=RequestMethod.POST)
	public void startProcessById(@RequestParam String processId) {
		cartableService.startProcessInstanceById(processId);
	}
	
	@RequestMapping(value="/process/startByKey/{processKey}",method=RequestMethod.POST)
	public void startProcessByKey(@PathVariable String processKey) {
		cartableService.startProcessInstanceByKey(processKey);
	}
	
	@RequestMapping("/task/list")
	@ResponseBody
	public List<TaskViewModel> getAthenticatedUserTasks() {
		List<Task> tasks= cartableService.getTasksByAuthenticatedUser();
		List<TaskViewModel> newList=new ArrayList<TaskViewModel>();
		for (Task task : tasks) {
			newList.add(dozerBeanMapper.map(task, TaskViewModel.class));
		}
		return newList;
	}
	
	@RequestMapping(value="/task/complete/{taskId}",method=RequestMethod.POST)
	public void completeTask(@PathVariable String taskId) {
		cartableService.completeTask(taskId);
	}
	
	@RequestMapping(value="/task/claim/{taskId}",method=RequestMethod.POST)
	public void claimTask(@PathVariable String taskId) {
		cartableService.claimTask(taskId);
	}
	
}
