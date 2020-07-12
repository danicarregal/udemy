
public class snippets{

    @Bean(name = "courses")
    public DefaultWsdl11Definition defaultWsdlDefinition(XsdSchema schema) {
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

    @PayloadRoot(namespace =
     "http://carregal.com/courses", localPart = "DeleteCourseDetailsRequest")
     @ResponsePayload public DeleteCourseDetailsResponse
     deleteCourseDetailsResquest(@RequestPayload DeleteCourseDetailsRequest
     request) { DeleteCourseDetailsResponse response = new
     DeleteCourseDetailsResponse();
     response.setResult(statusMapper.statusToOpStatus(service.deleteById(request.getId())));
     return response; }

}


