package br.com.cooperativism.config;

import br.com.cooperativism.exception.error.ApiError;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Autowired
  private TypeResolver typeResolver;

  @Autowired
  private DocumentationConfig documentationConfig;

  @Bean
  public Docket customImplementation() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(documentationConfig.getBasePackage()))
        .paths(PathSelectors.any()).build()
        .apiInfo(apiInfo(documentationConfig.getInfo().getTitle(), documentationConfig.getInfo().getDescription(),
            documentationConfig.getVersion()))
        .directModelSubstitute(LocalDate.class, String.class)
        .directModelSubstitute(LocalDateTime.class, String.class)
        .additionalModels(typeResolver.resolve(ApiError.class))
        .useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.GET, getResponseMessages())
        .globalResponseMessage(RequestMethod.POST, getResponseMessages())
        .globalResponseMessage(RequestMethod.PUT, getResponseMessages())
        .globalResponseMessage(RequestMethod.DELETE, getResponseMessages());

  }

  ApiInfo apiInfo(String title, String description, String version) {
    return new ApiInfoBuilder().title(title).description(description).version(version)
        .build();
  }

  private List<ResponseMessage> getResponseMessages() {
    return newArrayList(
        new ResponseMessageBuilder()
            .code(200).message("Request performed successfully.").build(),
        new ResponseMessageBuilder()
            .code(204).message("Without errors, but empty body.").build(),
        getErrorMessage(400, "Bad Request - Invalid parameter(s)."),
        getErrorMessage(404, "Resource(s) not found."),
        getErrorMessage(408, "Server timeout. Network error."),
        getErrorMessage(500, "Internal server error."));
  }

  private ResponseMessage getErrorMessage(Integer code, String message) {
    return new ResponseMessageBuilder()
        .code(code)
        .message(message)
        .responseModel(new ModelRef("Api Error"))
        .build();
  }

}
