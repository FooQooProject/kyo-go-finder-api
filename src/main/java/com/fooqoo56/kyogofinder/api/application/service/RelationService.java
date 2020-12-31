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
}
