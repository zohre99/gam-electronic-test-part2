package com.example.workflow.deligates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component()
public class SendMessage implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.getProcessEngineServices().getRuntimeService()
				.createMessageCorrelation("SeeInfo")
				.setVariable("notifyMessage", "Successful")
				.correlate();

	}

}
