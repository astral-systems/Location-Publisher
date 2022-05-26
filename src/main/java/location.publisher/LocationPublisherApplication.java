package location.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LocationPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationPublisherApplication.class, args);
	}

}
