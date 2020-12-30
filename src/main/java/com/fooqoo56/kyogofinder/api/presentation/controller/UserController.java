package com.fooqoo56.kyogofinder.api.presentation.controller;

import com.fooqoo56.kyogofinder.api.application.service.UserService;
import com.fooqoo56.kyogofinder.api.domain.model.User;
import com.fooqoo56.kyogofinder.api.presentation.dto.request.UserRequest;
import com.fooqoo56.kyogofinder.api.presentation.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @GetMapping(path = "/user/{id}")
    @ResponseBody
    public ApiResponse<User> getUser(@PathVariable final Integer id) {
        return userService.getUser(id);
    }

    /**
     * ユーザの保存
     *
     * @param userRequest ユーザ情報
     * @return APIレスポンス
     */
    @PostMapping(path = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<User> postUser(@Validated final UserRequest userRequest) {
        return userService.postUser(userRequest);
    }
}
