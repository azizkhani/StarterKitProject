package org.azizkhani.service.imp.cartable;

import java.util.List;

import org.activiti.engine.FormService;
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
import org.azizkhani.service.cartable.ICartableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartableService implements ICartableService {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ManagementService managementService;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;

	public List<ProcessDefinition> getProcessDefinitions() {
		return repositoryService.createProcessDefinitionQuery().list();
	}

	public void startProcessInstanceById(String processId) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId);
		System.out.println("id " + processInstance.getId() + " " + processInstance.getProcessDefinitionId());
	}

	public void startProcessInstanceByKey(String processKey) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey);
		System.out.println("id " + processInstance.getId() + " " + processInstance.getProcessDefinitionId());
	}
	public String getStartFormKeyByProcessId(String processId){
		return formService.getStartFormData(processId).getFormKey();
	}
	
	
	public void deploy(String processPath) {
		// process/myProcess.bpmn
		repositoryService.createDeployment().addClasspathResource(processPath).deploy();
	}

	public List<Deployment> queryDeployments() {
		List<Deployment> deps = repositoryService.createDeploymentQuery().list();
		for (Deployment proc : deps) {
			System.out.println(proc.getId());
		}
		return deps;
	}

	public void completeTask() {
		identityService.setAuthenticatedUserId("azizkhani");
		Task task = taskService.createTaskQuery().taskCandidateUser("azizkhani").singleResult();
		//task.getProcessVariables()
		taskService.complete(task.getId());
		// taskService.claim(task.getId(), user.getId()+"");
		System.out.println("task id " + task.getId() + ", name " + task.getName() + ", def key " + task.getTaskDefinitionKey());
	}

	public void identity() {
		if (identityService.createUserQuery().userId("azizkhani").list().size() == 0) {
			User newUser = identityService.newUser("azizkhani");
			identityService.saveUser(newUser);

			User user = identityService.createUserQuery().singleResult();

			Group newGroup = identityService.newGroup("sales");
			newGroup.setName("Sales");
			identityService.saveGroup(newGroup);

			Group group = identityService.createGroupQuery().singleResult();
			identityService.createMembership("azizkhani", "sales");
		}
		// setUrl(formService.getTaskFormData(taskId).getFormKey());
	}

	private void createUser(String user) {
		if (identityService.createUserQuery().userId(user).list().size() == 0) {
			User newUser = identityService.newUser(user);
			identityService.saveUser(newUser);
		}
	}

	private String getAuthenticatedUser() {
		org.azizkhani.model.security.User user = (org.azizkhani.model.security.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
	}

	@Override
	public List<Task> getTasksByUser(String user) {
		identityService.setAuthenticatedUserId(user);
		return taskService.createTaskQuery().taskCandidateUser(user).active().list();
	}

	@Override
	public List<Task> getTasksByAuthenticatedUser() {
		createUser(getAuthenticatedUser());
		identityService.setAuthenticatedUserId(getAuthenticatedUser());
		return taskService.createTaskQuery().taskCandidateUser(getAuthenticatedUser()).active().list();
	}

	@Override
	public void completeTask(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		taskService.complete(task.getId());
		System.out.println("completeTask"+task.getId()+" name"+task.getName());
	}

	@Override
	public void claimTask(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		taskService.claim(task.getId(), getAuthenticatedUser());
		System.out.println("claimTask"+task.getId()+" name"+task.getName());
	}

	@Override
	public List<ProcessInstance> getProcessInstance() {
		return runtimeService.createNativeProcessInstanceQuery().list();
	}
}
