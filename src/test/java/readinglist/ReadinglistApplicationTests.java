package readinglist;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadinglistApplication.class)
@WebAppConfiguration
class ReadinglistApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private WebApplicationContext webContext;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setupMockMvc() {
		
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webContext)
//				.apply(springSecurity()) //Para los tests con autenticación
				.build();
	}

	@Test
	public void homePage() throws Exception {
		
//		//Sin los imports static
//		mockMvc.perform(MockMvcRequestBuilders.get("/readingList"))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.view().name("readingList"))
//				.andExpect(MockMvcResultMatchers.model().attributeExists("books"))
//				.andExpect(MockMvcResultMatchers.model().attribute("books", Matchers.is(Matchers.empty())));
		
		mockMvc.perform(get("/api/pepe"))
		.andExpect(status().isOk())
		.andExpect(view().name("readingList"))
		.andExpect(model().attributeExists("books"))
		.andExpect(model().attribute("books", is(empty())));
		
	}	
	
//	@Test
	public void postBook() throws Exception {
		
		mockMvc.perform(post("/api/pepe")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("title", "BOOK TITLE")
						.param("author", "BOOK AUTHOR")
						.param("isbn", "1234567890")
						.param("description", "DESCRIPTION"))
						.andExpect(status().is3xxRedirection())
						.andExpect(header().string("Location", "/api/pepe"));
		
		Book expectedBook = new Book();
		expectedBook.setId(1L);
		expectedBook.setReader("pepe");
		expectedBook.setTitle("BOOK TITLE");
		expectedBook.setAuthor("BOOK AUTHOR");
		expectedBook.setIsbn("1234567890");
		expectedBook.setDescription("DESCRIPTION");
		
		mockMvc.perform(get("/api/pepe"))
				.andExpect(status().isOk())
				.andExpect(view().name("readingList"))
				.andExpect(model().attributeExists("books"))
				.andExpect(model().attribute("books", hasSize(1)))
				.andExpect(model().attribute("books", contains(samePropertyValuesAs(expectedBook))));
	}

//	@Test
	public void homePage_unauthenticatedUser() throws Exception {
		
		mockMvc.perform(get("/api"))
				.andExpect(status().isUnauthorized());
	}
	
//	@Test
	@WithUserDetails("111")
//	@WithMockUser(username = "111", password = "111", roles = "READER")
	public void homePage_authenticatedUser() throws Exception {
		
		mockMvc.perform(get("/api/pepe"))
				.andExpect(status().isOk())
				.andExpect(view().name("readingList"))
				.andExpect(model().attributeExists("books"))
				.andExpect(model().attribute("books", is(empty())));
	}
}





