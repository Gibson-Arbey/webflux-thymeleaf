package co.spring_webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebfluxApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringWebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxApplication.class, args);
		log.info("App running");
	}


}
