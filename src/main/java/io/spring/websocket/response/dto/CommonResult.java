package io.spring.websocket.response.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class CommonResult {
    private boolean success;
    private int code;
    private String message;
    @JsonIgnore
    private HttpStatus httpStatus = HttpStatus.OK;
}
