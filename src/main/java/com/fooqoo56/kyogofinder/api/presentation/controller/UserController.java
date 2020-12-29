package com.fooqoo56.kyogofinder.api.presentation.controller;

import com.fooqoo56.kyogofinder.api.application.service.UserService;
import com.fooqoo56.kyogofinder.api.presentation.dto.response.UserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    /**
     * ユーザの取得
     *
     * @return APIレスポンス
     */
    @GetMapping(path = "/user")
    public UserApiResponse getUser() {
        return userService.getUser();
    }

}
