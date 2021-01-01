package com.fooqoo56.kyogofinder.api.application.service;

import com.fooqoo56.kyogofinder.api.application.exception.FirestoreException;
import com.fooqoo56.kyogofinder.api.domain.model.User;
import com.fooqoo56.kyogofinder.api.domain.repository.FirestoreRepository;
import com.fooqoo56.kyogofinder.api.presentation.dto.form.UserRequest;
import com.fooqoo56.kyogofinder.api.presentation.dto.response.ApiResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@RequiredArgsConstructor
public class UserService {

    final FirestoreRepository fireStoreRepository;

    /**
     * ユーザの取得
     *
     * @return APIレスポンス
     */
    public ApiResponse<User> getUser(final Integer id) throws FirestoreException {
        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        try {
            return getApiResponse(fireStoreRepository.getUser(id), stopWatch);
        } catch (final Exception e) {
            throw new FirestoreException(e.getMessage());
        }
    }

    /**
     * ユーザの保存
     *
     * @param request ユーザ情報
     * @return APIレスポンス
     */
    public ApiResponse<User> postUser(final UserRequest request, final Integer userId)
            throws FirestoreException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final User user = User.convertFromRequestToUser(request);

        try {
            fireStoreRepository.writeUser(user, userId);
            return getApiResponse(user, stopWatch);
        } catch (final Exception e) {
            throw new FirestoreException(e.getMessage());
        }
    }

    /**
     * ユーザが存在するか
     *
     * @param id ユーザID
     * @return ユーザの有無
     */
    public boolean isExistUser(final Integer id) {
        try {
            final User user = fireStoreRepository.getUser(id);
            return Objects.nonNull(user);
        } catch (final Exception e) {
            return false;
        }
    }

    /**
     * ApiResponse取得
     *
     * @param user ユーザリスト
     * @return APIレスポンス
     */
    private ApiResponse<User> getApiResponse(final User user,
                                             final StopWatch stopWatch) {
        final LocalDateTime requestDate = LocalDateTime.now();
        final BigDecimal latency = BigDecimal.valueOf(stopWatch.getTotalTimeSeconds());
        return new ApiResponse<>(latency, requestDate, user);
    }
}
