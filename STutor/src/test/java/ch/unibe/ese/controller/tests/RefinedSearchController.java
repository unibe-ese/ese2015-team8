package ch.unibe.ese.controller.tests;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;


@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/springMVC.xml",
									"file:src/main/webapp/WEB-INF/config/springData.xml",
									"file:src/main/webapp/WEB-INF/config/springSecurity.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RefinedSearchController {

	@Autowired private WebApplicationContext wac;
	@Autowired private FilterChainProxy springSecurityFilterChain;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain))
				.build();
	}
	
	@Test
	public void testMappingRequest() throws Exception{
		ModelAndView mav = mockMvc.perform(post("/searchWFilters")
				.param("name", "lecture")
				.param("university", "-1")
				.param("subject", "-1")
				.param("gender", "male")
				.param("minGrade", "0")
				).andReturn().getModelAndView();
		
		assertViewName(mav, "searchResult");
	}
}
