package com.fooqoo56.kyogofinder.api.domain.model;

import com.fooqoo56.kyogofinder.api.presentation.dto.request.UserRequest;
import java.io.Serializable;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.lang.NonNull;

@Value
@Builder
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 8669195223323024928L;

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

    Boolean deleteFlag = false;

    /**
     * デフォルトコンストラクタ
     */
    public User() {
        id = -1;
        name = "";
        screenName = "";
        description = "";
        followersCount = 0;
        friendsCount = 0;
        statusesCount = 0;
        profileImageUrlHttps = "";
    }

    /**
     * リクエストからdatastore形式への変換
     *
     * @param request リクエスト
     * @return datastore形式
     */
    public static User convertFromRequestToUser(final UserRequest request) {
        return User.builder()
                .id(request.getId())
                .name(request.getName())
                .screenName(request.getScreenName())
                .description(request.getDescription())
                .followersCount(request.getFollowersCount())
                .friendsCount(request.getFriendsCount())
                .statusesCount(request.getStatusesCount())
                .profileImageUrlHttps(request.getProfileImageUrlHttps())
                .build();
    }
}
