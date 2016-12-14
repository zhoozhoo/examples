package ca.zhoozhoo.examples.watchservice.config;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class FolderCondition implements Condition {

	private static final Logger logger = LoggerFactory.getLogger(FolderCondition.class);

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String dirPath = context.getEnvironment().getProperty("watchservice.dirPath");
		File dir = new File(dirPath);

		if (!dir.exists()) {
			logger.error("Directory \"{}\" does not exist ", dirPath);
		}

		return dir.exists();
	}
}
