package com.fooqoo56.kyogofinder.api.presentation.dto.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.validation.constraints.Min;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 649628293446751739L;
    @NonNull
    Integer id;

    @NonNull
    String name;

    @NonNull
    String screenName;

    String description;

    @Min(0)
    Integer followersCount;

    @Min(0)
    Integer friendsCount;

    @Min(0)
    Integer statusesCount;

    String profileImageUrlHttps;
}
