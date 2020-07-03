package br.com.cooperativism.exception.error;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@ApiModel("Api Error")
public class ApiError implements Serializable {

  private static final long serialVersionUID = 8499150273690549026L;

  @ApiModelProperty
  private Date timestamp = Calendar.getInstance().getTime();

  @ApiModelProperty("Http status")
  private HttpStatus status = HttpStatus.BAD_REQUEST;

  @ApiModelProperty("Error message")
  private String message;

  @ApiModelProperty(notes = "Error detail")
  private String detail;

  public ApiError() {
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


  public static final class ApiErrorBuilder {

    private Date timestamp = Calendar.getInstance().getTime();
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private String message;
    private String detail;

    private ApiErrorBuilder() {
    }

    public static ApiErrorBuilder builder() {
      return new ApiErrorBuilder();
    }

    public ApiErrorBuilder timestamp(Date timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public ApiErrorBuilder status(HttpStatus status) {
      this.status = status;
      return this;
    }

    public ApiErrorBuilder message(String message) {
      this.message = message;
      return this;
    }

    public ApiErrorBuilder detail(String detail) {
      this.detail = detail;
      return this;
    }

    public ApiError build() {
      ApiError apiError = new ApiError();
      apiError.setTimestamp(timestamp);
      apiError.setStatus(status);
      apiError.setMessage(message);
      apiError.setDetail(detail);
      return apiError;
    }

  }

}
