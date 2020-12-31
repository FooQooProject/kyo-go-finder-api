package com.fooqoo56.kyogofinder.api.application.service;

import com.fooqoo56.kyogofinder.api.application.exception.FirestoreException;
import com.fooqoo56.kyogofinder.api.domain.model.Relation;
import com.fooqoo56.kyogofinder.api.domain.repository.FirestoreRepository;
import com.fooqoo56.kyogofinder.api.presentation.dto.form.FollowerFriendRequest;
import com.fooqoo56.kyogofinder.api.presentation.dto.form.FollowerRequest;
import com.fooqoo56.kyogofinder.api.presentation.dto.form.RelationRequest;
import com.fooqoo56.kyogofinder.api.presentation.dto.response.ApiResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
     * @param request ユーザ情報
     * @return APIレスポンス
     */
    public ApiResponse<RelationRequest> createRelation(final RelationRequest request)
            throws FirestoreException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final Integer userId = request.getId();

        try {
            fireStoreRepository.writeRelationUser(new Relation(), userId);

            final LocalDateTime requestDate = LocalDateTime.now();
            final BigDecimal latency = BigDecimal.valueOf(stopWatch.getTotalTimeSeconds());


            return new ApiResponse<>(latency, requestDate, request);
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
    public boolean isExistRelationUser(final Integer id) {
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
     * @param request ユーザ情報
     * @return APIレスポンス
     */
    public ApiResponse<FollowerRequest> createFollower(final FollowerRequest request)
            throws FirestoreException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final Integer userId = request.getId();

        try {
            fireStoreRepository
                    .writeRelationFollower(new Relation(), userId, request.getFollowerId());

            final LocalDateTime requestDate = LocalDateTime.now();
            final BigDecimal latency = BigDecimal.valueOf(stopWatch.getTotalTimeSeconds());

            return new ApiResponse<>(latency, requestDate, request);
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
    public boolean isExistRelationFollower(final Integer id, final Integer followerId) {
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
     * @param request ユーザ情報
     * @return APIレスポンス
     */
    public ApiResponse<FollowerFriendRequest> createFollowerFriend(
            final FollowerFriendRequest request)
            throws FirestoreException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final Integer userId = request.getId();

        try {
            fireStoreRepository
                    .writeRelationFollowerFriend(new Relation(), userId, request.getFollowerId(),
                            request.getFollowerFriendId());

            final LocalDateTime requestDate = LocalDateTime.now();
            final BigDecimal latency = BigDecimal.valueOf(stopWatch.getTotalTimeSeconds());

            return new ApiResponse<>(latency, requestDate, request);
        } catch (final Exception e) {
            throw new FirestoreException(e.getMessage());
        }
    }

    /**
     * RelationのFollowerのFriendにユーザが存在するか
     *
     * @param id ユーザID
     * @return ユーザの有無
     */
    public boolean isExistRelationFollowerFriend(final Integer id, final Integer followerId,
                                                 final Integer followerFriendId) {
        try {
            final Relation relation =
                    fireStoreRepository.getRelationFollowerFriend(id, followerId, followerFriendId);
            return Objects.nonNull(relation);
        } catch (final Exception e) {
            return false;
        }
    }
}
