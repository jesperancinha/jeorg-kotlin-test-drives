package com.steelzack.coffee.system.concurrency;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.manager.MachineProcessor;
import com.steelzack.coffee.system.objects.ActionDescriptor;
import lombok.Getter;
import org.apache.log4j.Logger;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Service
@Getter
public class PostActionCallableImpl extends ActionCallable implements PostActionCallable {

	@Autowired
	private MachineProcessor machineProcessor;

	private static final Logger logger = Logger.getLogger(PreActionCallableImpl.class);

	public PostActionCallableImpl(String name) {
		super(name);

	}

	@Override
	public void addPostAction(PostAction postAction) {
		this.actionDescriptorList
				.add(ActionDescriptor.builder().description(postAction.getDescription()).time(postAction.getTime())
						.build());
	}

	public Boolean call() {
		this.actionDescriptorList.stream().forEach(
				actionDescriptor -> {
					logger.info(MessageFormat.format("Ending with {0}", actionDescriptor.getDescription()));
					try {
						TimeUnit.MILLISECONDS.sleep(actionDescriptor.getTime());
					} catch (InterruptedException e) {
						logger.error(e);
					}
				}
		);
		return true;
	}

}
