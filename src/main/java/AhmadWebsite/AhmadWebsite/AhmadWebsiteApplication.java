package AhmadWebsite.AhmadWebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "AhmadWebsite.AhmadWebsite")
@EntityScan("AhmadWebsite.AhmadWebsite.models")
public class AhmadWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhmadWebsiteApplication.class, args);
	}

}
