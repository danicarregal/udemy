package com.in28minutes.springboot.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springboot.Application;
import com.in28minutes.springboot.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@LocalServerPort
	private int port;

	private TestRestTemplate template;
	private HttpHeaders headers;

	@Before
	public void init() {
		template = new TestRestTemplate();
		headers = createHeaders("user", "secret1");
	}

	private HttpHeaders createHeaders(String userId, String password) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String auth = "user" + ":" + "secret1";
		byte[] encondeAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
		String headerValue = "Basic " + new String(encondeAuth);
		headers.add("Authorization", headerValue);

		return headers;
	}

	private String createUrl(String path) {

		return "http://localhost:" + port + path;
	}

	@Test
	public void testJSONAssertRetrieveSurveyQuestion() {

		JSONAssert.assertEquals("{date: 1}", "{\"date\": 1}", false);
	}

	@Test
	public void testRetrieveSurveyQuestion() {

		String url = createUrl("/surveys/Survey1/questions/Question1");
		System.out.println(url);

		HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, headers);
		ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, requestEntity, String.class);

		String body = response.getBody();
		System.out.println("Response : " + body);

		// {"id":"Question1","description":"Largest Country in the
		// World","correctAnswer":"Russia","options":["India","Russia","United
		// States","China"]}

		assertTrue(body.contains("\"id\":\"Question1\""));
		assertTrue(body.contains("\"correctAnswer\":\"Russia\""));

		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";
		JSONAssert.assertEquals("{date:1}", "{date: 1}", false);
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	@Test
	public void testRetrieveSurveyQuestions() {

		String url = createUrl("/surveys/Survey1/questions/");
		System.out.println(url);

		HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, headers);
		@SuppressWarnings("rawtypes")
		ResponseEntity<ArrayList> response = template.exchange(url, HttpMethod.GET, requestEntity, ArrayList.class);

		@SuppressWarnings("unchecked")
		ArrayList<Question> body = response.getBody();
		System.out.println("Response : " + body);

	}

	@Test
	public void testRetrieveSurveyQuestions2() {

		String url = createUrl("/surveys/Survey1/questions");
		System.out.println(url);

		HttpEntity<Question> requestEntity = new HttpEntity<Question>(null, headers);

		ResponseEntity<List<Question>> response = template.exchange(url, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<Question>>() {
				});

		Question assertQuestion = new Question("Question1", "Largest Country in the World", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));
		assertTrue(response.getBody().contains(assertQuestion));
	}

	@Test
	public void testAddSurveyQuestions() {

		String url = createUrl("/surveys/Survey1/questions");
		System.out.println(url);

		Question newQuestion = new Question("Question10", "Daniel penis size", "16",
				Arrays.asList("13", "14", "15", "16"));

		HttpEntity<Question> requestEntity = new HttpEntity<Question>(newQuestion, headers);

		ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, requestEntity, String.class);

		List<String> headers = response.getHeaders().get(HttpHeaders.LOCATION);
		String location = headers.get(0);

		assertTrue(location.contains("/surveys/Survey1/questions/"));
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

	}
}
