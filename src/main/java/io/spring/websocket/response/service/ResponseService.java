package io.spring.websocket.response.service;

import io.spring.websocket.response.dto.CommonResult;
import io.spring.websocket.response.dto.SingleResult;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static io.spring.websocket.response.service.ResponseService.CommonResponse.SUCCESS;

@Component
public record ResponseService() {

    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setResult(data);
        setSuccessResult(result);

        return result;
    }


    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(SUCCESS.getCode());
        result.setMessage(SUCCESS.getMessage());
    }


    @Getter
    public enum CommonResponse {
        SUCCESS(0, "SUCCESS"),
        FAIL(-1, "FAIL");

        final int code;
        final String message;

        CommonResponse(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

}
