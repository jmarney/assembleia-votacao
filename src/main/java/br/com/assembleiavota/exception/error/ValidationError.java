package br.com.cooperativism.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final List<String> errors = new ArrayList<>();

  private final String errorMessage;

  public ValidationError(final String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void addValidationError(final String error) {
    errors.add(error);
  }

  public List<String> getErrors() {
    return errors;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
