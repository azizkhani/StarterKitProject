package org.azizkhani.service.cartable;

import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public interface ICartableService {
	public List<ProcessDefinition> getProcessDefinitions();
	public List<ProcessInstance> getProcessInstance();
	public void startProcessInstanceById(String processId);
	public void startProcessInstanceByKey(String processKey);
	public List<Task> getTasksByUser(String user);
	public List<Task> getTasksByAuthenticatedUser();
	public void completeTask(String taskId);
	public void claimTask(String taskId);
}
