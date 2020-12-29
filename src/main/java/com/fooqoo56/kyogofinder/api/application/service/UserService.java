package com.fooqoo56.kyogofinder.api.application.service;

import com.fooqoo56.kyogofinder.api.presentation.dto.response.UserApiResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {


    /**
     * ユーザの取得
     *
     * @return APIレスポンス
     */
    public UserApiResponse getUser() {
        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        final UserApiResponse.User[] users = {new UserApiResponse.User(0, "taro")};

        return getApiResponse(Arrays.asList(users), stopWatch);
    }

    /**
     * ApiResponse取得
     *
     * @param userList ユーザリスト
     * @return APIレスポンス
     */
    private UserApiResponse getApiResponse(final List<UserApiResponse.User> userList, final StopWatch stopWatch) {
        final LocalDateTime requestDate = LocalDateTime.now();
        final BigDecimal latency = BigDecimal.valueOf(stopWatch.getTotalTimeSeconds());
        return new UserApiResponse(latency, requestDate, userList);
    }
}
