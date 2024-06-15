package io.spring.websocket.response.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SingleResult<T> extends CommonResult {
    private T result;
}
