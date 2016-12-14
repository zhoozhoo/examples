package ca.zhoozhoo.examples.watchservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import ca.zhoozhoo.examples.watchservice.service.DirectoryMonitorService;

@SpringBootApplication
@EnableAsync
public class Application {

	@Autowired
	private DirectoryMonitorService directoryMonitorService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args); // NOSONAR
	}

	@Bean
	public ApplicationRunner startDirectoryMonitorService() {
		return args -> directoryMonitorService.monitorSourceDirectoty();
	}
}