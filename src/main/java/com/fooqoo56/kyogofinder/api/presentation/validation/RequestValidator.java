package com.fooqoo56.kyogofinder.api.presentation.validation;

import com.fooqoo56.kyogofinder.api.application.service.RelationService;
import com.fooqoo56.kyogofinder.api.application.service.UserService;
import com.fooqoo56.kyogofinder.api.presentation.dto.form.FollowerFriendRequest;
import com.fooqoo56.kyogofinder.api.presentation.dto.form.FollowerRequest;
import com.fooqoo56.kyogofinder.api.presentation.dto.form.RelationRequest;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestValidator {

    private final RelationService relationService;
    private final UserService userService;

    /**
     * RelationRequestのバリデータ
     *
     * @param request RelationRequest
     * @return RelationRequestに存在するかどうか
     */
    public boolean validateRelationRequest(final RelationRequest request) {
        return !relationService.isExistRelationUser(request.getId()) &&
                userService.isExistUser(request.getId());
    }

    /**
     * FollowerRequestのバリデータ
     *
     * @param request FollowerRequest
     * @return FollowerRequestが存在するかどうか
     */
    public boolean validateFollowerRequest(final FollowerRequest request) {
        return !relationService.isExistRelationFollower(request.getId(), request.getFollowerId()) &&
                relationService.isExistRelationUser(request.getId()) &&
                !isSelfFollow(request.getId(), request.getFollowerId()) &&
                userService.isExistUser(request.getId()) &&
                userService.isExistUser(request.getFollowerId());
    }

    /**
     * FollowerFriendRequestのバリデータ
     *
     * @param request FollowerFriendRequest
     * @return FollowerFriendRequestが存在するかどうか
     */
    public boolean validateFollowerFriendRequest(final FollowerFriendRequest request) {
        return !relationService
                .isExistRelationFollowerFriend(request.getId(), request.getFollowerId(),
                        request.getFollowerFriendId()) &&
                relationService
                        .isExistRelationFollower(request.getId(), request.getFollowerId()) &&
                !isSelfFollow(request.getId(), request.getFollowerId()) &&
                userService.isExistUser(request.getId()) &&
                userService.isExistUser(request.getFollowerId()) &&
                userService.isExistUser(request.getFollowerFriendId());

    }

    /**
     * 自身をフォローしてるかどうか
     *
     * @param userId     ユーザID
     * @param followerId フォロー
     * @return 自身をフォローしてるかどうか
     */
    private boolean isSelfFollow(final Integer userId, final Integer followerId) {
        if (Objects.nonNull(userId)) {
            return userId.equals(followerId);
        }
        return false;
    }
}
