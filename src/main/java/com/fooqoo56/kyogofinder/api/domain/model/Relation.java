package com.fooqoo56.kyogofinder.api.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Relation implements Serializable {

    private static final long serialVersionUID = 58210621368715635L;

    LocalDate updatedAt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo")).toLocalDate();

    Boolean lastSucceedFlag = false;

    Boolean deleteFlag = false;
}
