package br.com.cooperativism.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class AbstractException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = -5603660003741702875L;
  private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

  public AbstractException(final String msg) {
    super(msg);
  }

  public AbstractException(final String msg, final Throwable throwable) {
    super(msg, throwable);
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

}
