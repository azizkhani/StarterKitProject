package org.azizkhani.service.imp.cartable;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

public class DroolsDelegate implements JavaDelegate {

	private Expression drlFile;
	private Expression facts;

	private HashMap<String, Object> factMap = new HashMap<String, Object>();

	public void execute(DelegateExecution execution) throws Exception {
		
//		String drlFileName = (String) drlFile.getValue(execution);
//
//		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//		kbuilder.add(ResourceFactory.newClassPathResource(drlFileName, getClass()), ResourceType.DRL);
//		if (kbuilder.hasErrors()) {
//			throw new RuntimeException("Error in drools: " + kbuilder.getErrors().toString());
//		}
//		KnowledgeBase knowledgeBase = kbuilder.newKnowledgeBase();
//		StatefulKnowledgeSession workingMemory = knowledgeBase.newStatefulKnowledgeSession();
//
//		if (facts != null) {
//			// Very easy implementation to fetch the parameters :-) Must be
//			// improved for real live
//			StringTokenizer st = new StringTokenizer((String) facts.getValue(execution), ",");
//			while (st.hasMoreTokens()) {
//				String variableName = st.nextToken().trim();
//				Object variable = execution.getVariable(variableName);
//				workingMemory.insert(variable);
//				factMap.put(variableName, variable);
//			}
//		}
//		workingMemory.fireAllRules();
//
//		// update variables
//		for (Entry<String, Object> factEntry : factMap.entrySet()) {
//			execution.setVariable(factEntry.getKey(), factEntry.getValue());
//		}
	}

}
