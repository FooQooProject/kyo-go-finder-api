package com.fooqoo56.kyogofinder.api.application.service;

import com.fooqoo56.kyogofinder.api.application.exception.FirestoreException;
import com.fooqoo56.kyogofinder.api.domain.model.Relation;
import com.fooqoo56.kyogofinder.api.domain.repository.FirestoreRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@RequiredArgsConstructor
@Slf4j
public class RelationService {

    final FirestoreRepository fireStoreRepository;

    /**
     * Relationの新規作成
     *
     * @param userId ユーザID
     * @throws FirestoreException FireStoreの独自例外
     */
    public void createRelation(final String userId)
            throws FirestoreException {

        try {
            fireStoreRepository.writeRelationUser(new Relation(), userId);
        } catch (final Exception e) {
            throw new FirestoreException(e.getMessage());
        }
    }

    /**
     * Relationにユーザが存在するか
     *
     * @param id ユーザID
     * @return ユーザの有無
     */
    public boolean isExistRelationUser(final String id) {
        try {
            final Relation relation = fireStoreRepository.getRelationUser(id);
            return Objects.nonNull(relation);
        } catch (final Exception e) {
            return false;
        }
    }

    /**
     * Relationのfollowerの新規作成
     *
     * @param userId     ユーザ情報
     * @param followerId フォロワーのID
     * @throws FirestoreException FireStoreの独自例外
     */
    public void createFollower(final String userId, final String followerId)
            throws FirestoreException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            fireStoreRepository
                    .writeRelationFollower(new Relation(), userId, followerId);
        } catch (final Exception e) {
            throw new FirestoreException(e.getMessage());
        }
    }

    /**
     * Relationのフォロワーにユーザが存在するか
     *
     * @param id ユーザID
     * @return ユーザの有無
     */
    public boolean isExistRelationFollower(final String id, final String followerId) {
        try {
            final Relation relation = fireStoreRepository.getRelationFollower(id, followerId);
            return Objects.nonNull(relation);
        } catch (final Exception e) {
            return false;
        }
    }

    /**
     * Relationのfollowerのfriendの新規作成
     *
     * @param userId           ユーザID
     * @param followerId       フォロワーのユーザID
     * @param followerFriendId フォロワーのフォローユーザのID
     * @throws FirestoreException FireStoreの独自例外
     */
    public void createFollowerFriend(
            final String userId, final String followerId, final String followerFriendId)
            throws FirestoreException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            fireStoreRepository
                    .writeRelationFollowerFriend(new Relation(), userId, followerId,
                            followerFriendId);
        } catch (final Exception e) {
            throw new FirestoreException(e.getMessage());
        }
    }
}
