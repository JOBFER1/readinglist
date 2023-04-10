package readinglist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ReadinglistApplication 
//extends SpringBootServletInitializer
{

	public static void main(String[] args) {
		SpringApplication.run(ReadinglistApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoaderUsers(ReaderRepository readerRepo, PasswordEncoder encoder) { 
		
		return args -> {
			readerRepo.deleteAll(); 
			//Sin campo ID
			readerRepo.save(new Reader("111","Pepe", encoder.encode("111")));
		};
	}

}
