package com.fooqoo56.kyogofinder.api.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Value;


@Value
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -2583300223872852072L;

    @JsonProperty("Code")
    Integer code;

    @JsonProperty("Message")
    String message;
}
