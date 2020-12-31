package com.fooqoo56.kyogofinder.api.presentation.dto.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RelationRequest implements Serializable {
    private static final long serialVersionUID = -5147826274939045790L;

    @NonNull
    Integer id;
}
