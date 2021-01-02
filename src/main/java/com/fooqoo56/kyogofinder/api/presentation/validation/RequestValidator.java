package com.fooqoo56.kyogofinder.api.presentation.validation;

import com.fooqoo56.kyogofinder.api.application.service.RelationService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestValidator {

    private final RelationService relationService;

    /**
     * Followerに保存済み && 自身へのフォローでない
     *
     * @param userId     ユーザID
     * @param followerId フォロワーのユーザID
     * @return バリデーション結果
     */
    public boolean validateFollowerRequest(final String userId, final String followerId) {
        return relationService.isExistRelationUser(userId) && isNotSelfFollow(userId, followerId);
    }

    /**
     * Follower/Friendに保存済み && 自身へのフォローでない
     *
     * @param userId     ユーザID
     * @param followerId フォロワーのユーザID
     * @return バリデーション結果
     */
    public boolean validateFollowerFriendRequest(final String userId, final String followerId) {
        return relationService.isExistRelationFollower(userId, followerId) &&
                isNotSelfFollow(userId, followerId);

    }

    /**
     * 自身をフォローしてないかどうか
     *
     * @param userId     ユーザID
     * @param followerId フォロー
     * @return 自身をフォローしてるないかどうか
     */
    private boolean isNotSelfFollow(final String userId, final String followerId) {
        if (Objects.nonNull(userId)) {
            return !userId.equals(followerId);
        }
        return false;
    }
}
