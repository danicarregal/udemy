package com.carregal.SpringSoap.soap;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		// TODO Auto-generated method stub
		interceptors.add(securityInterceptor());
		super.addInterceptors(interceptors);
	}

	// MessageDispatcherServlet
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {

		MessageDispatcherServlet mds = new MessageDispatcherServlet();
		mds.setApplicationContext(context);
		mds.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(mds, "/ws/*");
	}

	// /ws/courses.wsdl
	// namespace
	// xsd
	// PortType - CoursePort
	@Bean(name = "courses")
	public DefaultWsdl11Definition defaultWsdlDefinition(XsdSchema schema) {

		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("CoursePort");
		definition.setSchema(schema);
		definition.setTargetNamespace("http://carregal.com/courses");
		definition.setLocationUri("/ws");
		return definition;
	}

	@Bean
	XsdSchema getCoursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}

	@Bean
	public XwsSecurityInterceptor securityInterceptor() {

		XwsSecurityInterceptor interceptor = new XwsSecurityInterceptor();
		interceptor.setCallbackHandler(callbackHandler());
		interceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));

		return interceptor;
	}

	@Bean
	public SimplePasswordValidationCallbackHandler callbackHandler() {

		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		handler.setUsersMap(Collections.singletonMap("danielcro", "dani01"));
		return handler;
	}

}
