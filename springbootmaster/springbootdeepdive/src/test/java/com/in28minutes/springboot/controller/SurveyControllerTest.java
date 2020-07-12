package com.in28minutes.springboot.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.in28minutes.springboot.model.Question;
import com.in28minutes.springboot.service.SurveyService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SurveyController.class, secure = false)
public class SurveyControllerTest {

	/*
	 * @Mock private SurveyService service;
	 * 
	 * @InjectMocks private SurveyController controller;
	 */

	@MockBean
	private SurveyService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void retrieveDetailsForQuestionTest() throws Exception {

		Question question1 = new Question("Question1", "Largest Country in the World", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		// ajustando el mock. Lo que probamos es el controller.
		// when(service.retrieveQuestion("Survey1", "Question1")).thenReturn(question1);
		when(service.retrieveQuestion(Mockito.anyString(), Mockito.anyString())).thenReturn(question1);

		// tenemos que llamar con GET y ver los datos que recibimos. El controlador ya
		// tiene inyectado el service.
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/Survey1/questions/Question1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";
		String contentAsString = result.getResponse().getContentAsString();

		JSONAssert.assertEquals(expected, contentAsString, false);
	}

	@Test
	public void retrieveQuestionsTest() throws Exception {

		Question question1 = new Question("Question1", "Largest Country in the World", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		Question question2 = new Question("Question2", "Smallest Country in the World", "San Marino",
				Arrays.asList("San Marino", "Monaco", "Batican", "Andorra"));

		when(service.retrieveQuestions(Mockito.anyString())).thenReturn(Arrays.asList(question1, question2));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/SurveyDummyId/questions")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

		String expected = "[{id:Question1,description:Largest Country in the World,correctAnswer:Russia},{id:Question2,description:Smallest Country in the World,correctAnswer:San Marino}]";
		String contentAsString = result.getResponse().getContentAsString();

		JSONAssert.assertEquals(expected, contentAsString, false);
	}

	@Test
	public void addQuestionToSurveyTest() throws Exception {

		Question mockQuestion = new Question("1", "Smallest Number", "1", Arrays.asList("1", "2", "3", "4"));

		String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";

		when(service.addQuestion(Mockito.anyString(), Mockito.any(Question.class))).thenReturn(mockQuestion);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/surveys/SurveyDummyId/questions")
				.content(questionJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn();

		String contentAsString = result.getResponse().getContentAsString();

		String location = result.getResponse().getHeader(HttpHeaders.LOCATION);
		System.out.println(location);
	}

}
