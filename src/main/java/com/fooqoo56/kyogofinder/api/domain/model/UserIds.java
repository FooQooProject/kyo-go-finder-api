package com.fooqoo56.kyogofinder.api.domain.model;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserIds implements Serializable {

    List<String> userIds;
}
