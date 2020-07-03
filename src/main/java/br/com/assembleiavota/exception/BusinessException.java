package br.com.assembleiavota.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class BusinessException extends AbstractException implements Serializable {

  private static final long serialVersionUID = 7298112717525882439L;

  public BusinessException(final String msg) {
    super(msg);
  }

  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }

}
