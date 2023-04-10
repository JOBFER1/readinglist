package readinglist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadinglistApplication.class)
public class SimpleWebTest {

	@Test
	public void pageNotFound() { 
		
		TestRestTemplate testRestTemplate1 = new TestRestTemplate();  
		
		String url1 = "http://localhost:8089/zzz";
//		String url1 = "http://localhost:8089/api/pepe";
		//Da igual a qué url vayamos, que siempre devuelve OK porque nos lleva a la página de login
		
		ResponseEntity<String> response1  = testRestTemplate1
							.getForEntity(url1, String.class);		
		
		Assertions.assertEquals(response1.getStatusCode(), HttpStatus.OK); 
		
		//----------------------------------------
		
		String url2 = "http://localhost:8089/api/pepe";
		
		TestRestTemplate testRestTemplate2 = new TestRestTemplate("111", "111");
		ResponseEntity<String> response2  = testRestTemplate2
							.getForEntity(url2, String.class);		
		
//		TestRestTemplate testRestTemplate2 = new TestRestTemplate();  
//		ResponseEntity<String> response2  = testRestTemplate2
//							.withBasicAuth("111", "111")
//							.getForEntity(url2, String.class);		
		
		//Ambas formas son equivalentes
		
		Assertions.assertEquals(response2.getStatusCode(), HttpStatus.OK); 
		//Devuelve OK porque nos lleva a la vista readingList

		//----------------------------------------
		
		TestRestTemplate testRestTemplate3 = new TestRestTemplate("111", "111");
		
		String url3 = "http://localhost:8089/zzz";
		
		ResponseEntity<String> response3  = testRestTemplate3
							.getForEntity(url3, String.class);		
		
		Assertions.assertEquals(response3.getStatusCode(), HttpStatus.NOT_FOUND); 
		//Devuelve NOT_FOUND porque nos lleva a la página de error
		
	}
	
}
