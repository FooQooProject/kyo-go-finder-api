package com.fooqoo56.kyogofinder.api.presentation.dto.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FollowerFriendRequest implements Serializable {
    private static final long serialVersionUID = 1625679948967051569L;

    @NonNull
    Integer id;

    @NonNull
    Integer followerId;

    @NonNull
    Integer followerFriendId;
}
