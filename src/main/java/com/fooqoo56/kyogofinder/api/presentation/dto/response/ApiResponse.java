package com.fooqoo56.kyogofinder.api.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Value;

@Value
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -2327371660414097693L;

    BigDecimal latency;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime requestDate;

    T result;
}
