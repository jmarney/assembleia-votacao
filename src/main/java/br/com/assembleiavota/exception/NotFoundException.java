package br.com.assembleiavota.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class NotFoundException extends AbstractException implements Serializable {

  private static final long serialVersionUID = -7236793513290576784L;

  public NotFoundException(final String msg) {
    super(msg);
  }

  public HttpStatus getHttpStatus() {
    return HttpStatus.NOT_FOUND;
  }

}
