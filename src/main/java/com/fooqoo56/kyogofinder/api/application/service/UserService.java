package com.fooqoo56.kyogofinder.api.application.service;

import com.fooqoo56.kyogofinder.api.domain.model.User;
import com.fooqoo56.kyogofinder.api.domain.repository.FireStoreRepository;
import com.fooqoo56.kyogofinder.api.presentation.dto.request.UserRequest;
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
public class UserService {

    final FireStoreRepository fireStoreRepository;

    /**
     * ユーザの取得
     *
     * @return APIレスポンス
     */
    public ApiResponse<User> getUser(final Integer id) {
        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        try {
            return getApiResponse(fireStoreRepository.getUser(id), stopWatch);
        } catch (final Exception e) {
            log.error(String.format("エラーログ:%s", e.getMessage()));
            return getApiResponse(User.builder().build(), stopWatch);
        }
    }

    /**
     * ユーザの保存
     *
     * @param request ユーザ情報
     * @return APIレスポンス
     */
    public ApiResponse<User> postUser(final UserRequest request) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final User user = User.convertFromRequestToUser(request);

        try {
            fireStoreRepository.writeUser(user);
            return getApiResponse(user, stopWatch);
        } catch (final Exception e) {
            log.error(String.format("エラーログ:%s", e.getMessage()));
            return getApiResponse(null, stopWatch);
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
