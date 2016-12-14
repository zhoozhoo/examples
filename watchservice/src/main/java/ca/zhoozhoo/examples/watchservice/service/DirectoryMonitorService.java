package ca.zhoozhoo.examples.watchservice.service;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DirectoryMonitorService {

	private static final Logger logger = LoggerFactory.getLogger(DirectoryMonitorService.class);

	@Value("${watchservice.dirPath}")
	private String dirPath;

	@Autowired
	private WatchService watchService;

	@Async
	public void monitorSourceDirectoty() throws IOException {
		while (true) {
			WatchKey watchKey;
			try {
				watchKey = watchService.take();
			} catch (ClosedWatchServiceException | InterruptedException e) {
				logger.error("Exception occured while waiting for files being added to source directory", e);
				return;
			}

			for (WatchEvent<?> event : watchKey.pollEvents()) {
				if (event.kind() == OVERFLOW) {
					continue;
				}

				@SuppressWarnings("unchecked")
				WatchEvent<Path> ev = (WatchEvent<Path>) event;
				Path filePath = ev.context();
				Path absoluteFilePath = Paths.get(dirPath).resolve(filePath);

				if (event.kind() == ENTRY_CREATE) {
					processCreate(absoluteFilePath);
				} else if (event.kind() == ENTRY_DELETE) {
					processDelete(absoluteFilePath);
				} else if (event.kind() == ENTRY_MODIFY) {
					processModify(absoluteFilePath);
				}
			}

			if (!watchKey.reset()) {
				break;
			}
		}

		watchService.close();
	}

	@Async
	private void processCreate(Path filePath) {
		logger.info("File \"{}\" created", filePath);
	}

	@Async
	private void processDelete(Path filePath) {
		logger.info("File \"{}\" deleted", filePath);
	}

	@Async
	private void processModify(Path filePath) {
		logger.info("File \"{}\" modified", filePath);
	}
}
