package com.fooqoo56.kyogofinder.api.presentation.controller;

import com.fooqoo56.kyogofinder.api.application.exception.ValidationException;
import com.fooqoo56.kyogofinder.api.application.service.RelationService;
import com.fooqoo56.kyogofinder.api.application.service.UserService;
import com.fooqoo56.kyogofinder.api.domain.model.User;
import com.fooqoo56.kyogofinder.api.domain.model.UserIds;
import com.fooqoo56.kyogofinder.api.presentation.dto.form.UserRequest;
import com.fooqoo56.kyogofinder.api.presentation.dto.response.ApiResponse;
import com.fooqoo56.kyogofinder.api.presentation.validation.RegisterdId;
import com.fooqoo56.kyogofinder.api.presentation.validation.RequestValidator;
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
@Validated
public class UserController {

    private final UserService userService;
    private final RelationService relationService;
    private final RequestValidator validator;

    /**
     * ID指定のユーザの取得
     *
     * @param id ユーザID
     * @return APIレスポンス
     */
    @GetMapping(path = "/user/{id}")
    @ResponseBody
    public ApiResponse<User> getUser(@PathVariable("id") final String id) {
        return userService.getUser(id);
    }

    /**
     * 条件指定のユーザの取得
     *
     * @return APIレスポンス
     */
    @GetMapping(path = "/user/oldest")
    @ResponseBody
    public ApiResponse<UserIds> getUser() {
        return userService.getUsers();
    }

    /**
     * ユーザの新規作成・上書き
     *
     * @param id          ユーザID
     * @param userRequest ユーザ情報
     * @return APIレスポンス
     */
    @PostMapping(path = "/user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<User> postUser(@PathVariable("id") final String id,
                                      final UserRequest userRequest) {
        final ApiResponse<User> response = userService.postUser(userRequest, id);
        relationService.createRelation(id);
        return response;
    }

    /**
     * フォロワーの新規作成・上書き
     *
     * @param id          ユーザID
     * @param followerId  フォロワーのユーザID
     * @param userRequest ユーザ情報
     * @return APIレスポンス
     * @throws ValidationException バリデーションの独自例外
     */
    @PostMapping(path = "/user/{id}/follower/{followerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<User> postUser(@PathVariable("id") @RegisterdId final String id,
                                      @PathVariable("followerId") final String followerId,
                                      final UserRequest userRequest) throws ValidationException {
        if (validator.validateFollowerRequest(id, followerId)) {
            final ApiResponse<User> response = userService.postUser(userRequest, followerId);
            relationService.createFollower(id, followerId);
            return response;
        } else {
            throw new ValidationException(
                    ValidationException.ValidationMessage.PARAMETER_VALID_ERROR,
                    "上位層が未登録、または、自身へのフォローが発生してます");
        }

    }

    /**
     * フォロワーのフォローユーザの新規作成・上書き
     *
     * @param id               ユーザID
     * @param followerId       フォロワーのユーザID
     * @param followerFriendId フォロワーのフォローユーザのID
     * @param userRequest      ユーザ情報
     * @return APIレスポンス
     * @throws ValidationException バリデーションの独自例外
     */
    @PostMapping(path = "/user/{id}/follower/{followerId}/friend/{followerFriendId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<User> postUser(@PathVariable("id") @RegisterdId final String id,
                                      @PathVariable("followerId") @RegisterdId
                                      final String followerId,
                                      @PathVariable("followerFriendId")
                                      final String followerFriendId,
                                      final UserRequest userRequest) throws ValidationException {
        if (validator.validateFollowerFriendRequest(id, followerId)) {
            final ApiResponse<User> response = userService.postUser(userRequest, followerFriendId);
            relationService.createFollowerFriend(id, followerId, followerFriendId);
            return response;
        } else {
            throw new ValidationException(
                    ValidationException.ValidationMessage.PARAMETER_VALID_ERROR,
                    "上位層が未登録、または、自身へのフォローが発生してます");
        }

    }
}
