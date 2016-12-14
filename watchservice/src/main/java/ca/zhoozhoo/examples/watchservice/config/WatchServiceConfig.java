package ca.zhoozhoo.examples.watchservice.config;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.WatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WatchServiceConfig {

	private static final Logger logger = LoggerFactory.getLogger(WatchServiceConfig.class);

	@Value("${watchservice.dirPath}")
	private String dirPath;

	@Bean
	@Conditional(FolderCondition.class)
	public WatchService watchService() throws IOException {
		WatchService watchService = FileSystems.getDefault().newWatchService();
		Paths.get(dirPath).register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		logger.info("Started watching \"{}\" directory ", dirPath);

		return watchService;
	}
}
