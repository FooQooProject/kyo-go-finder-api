package com.fooqoo56.kyogofinder.api.presentation.controller;

import com.fooqoo56.kyogofinder.api.application.service.RelationService;
import com.fooqoo56.kyogofinder.api.presentation.dto.form.FollowerFriendRequest;
import com.fooqoo56.kyogofinder.api.presentation.dto.form.FollowerRequest;
import com.fooqoo56.kyogofinder.api.presentation.dto.form.RelationRequest;
import com.fooqoo56.kyogofinder.api.presentation.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class RelationController {

    final RelationService relationService;

    /**
     * ユーザの保存
     *
     * @param request ユーザ情報
     * @return APIレスポンス
     */
    @PostMapping(path = "/relation/")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RelationRequest> createRelation(@Validated final RelationRequest request) {
        return relationService.createRelation(request);
    }

    /**
     * ユーザの保存
     *
     * @param request ユーザ情報
     * @return APIレスポンス
     */
    @PostMapping(path = "/relation/follower")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<FollowerRequest> createRelation(@Validated final FollowerRequest request) {
        return relationService.createFollower(request);
    }

    /**
     * ユーザの保存
     *
     * @param request ユーザ情報
     * @return APIレスポンス
     */
    @PostMapping(path = "/relation/follower/friend")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<FollowerFriendRequest> createRelation(
            @Validated final FollowerFriendRequest request) {
        return relationService.createFollowerFriend(request);
    }
}
