package br.com.cooperativism.exception.error;

import com.google.common.base.Strings;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationErrorBuilder {

  public static ValidationError fromBindingErrors(final Errors errors) {
    final ValidationError error = new ValidationError(
        "Validação de campos. " + errors.getErrorCount() + " erro(s)");
    for (final ObjectError objectError : errors.getAllErrors()) {
      final String field = ((FieldError) objectError).getField();
      final String msg = objectError.getDefaultMessage();
      if (Strings.isNullOrEmpty(msg) || !msg.toLowerCase().contains("campo")) {
        error.addValidationError(
            "Campo '" + field + "': " + msg);
      } else {
        error.addValidationError(msg);
      }
    }
    return error;
  }
}
