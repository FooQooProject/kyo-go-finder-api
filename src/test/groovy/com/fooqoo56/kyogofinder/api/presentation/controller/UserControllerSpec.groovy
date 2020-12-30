package com.fooqoo56.kyogofinder.api.presentation.controller

import com.fooqoo56.kyogofinder.api.application.service.UserService
import com.fooqoo56.kyogofinder.api.domain.model.User
import com.fooqoo56.kyogofinder.api.presentation.dto.request.UserRequest
import com.fooqoo56.kyogofinder.api.presentation.dto.response.ApiResponse
import com.google.cloud.firestore.Firestore
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerSpec extends Specification {

    UserService userService
    UserController target
    MockMvc mockMvc
    Firestore firestore

    final setup() {
        userService = Mock(UserService)
        firestore = Mock(Firestore)
        target = new UserController(userService)
        mockMvc = MockMvcBuilders.standaloneSetup(target).build()
    }

    @Unroll
    final "ユーザ取得 - getUser"() {

        given:
        final user = User.builder()
                .id(0)
                .name(name)
                .screenName("fooqoo17")
                .description("")
                .followersCount(10)
                .friendsCount(10)
                .statusesCount(10)
                .profileImageUrlHttps("http://localhost/")
                .build()

        final apiResponse = new ApiResponse(1.00, LocalDateTime.now(), user)
        userService.getUser() >> apiResponse

        when:
        final actual = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/user/0")
        ).andReturn()

        then:
        actual.response.getStatus() == httpStatus

        where:
        name     || httpStatus
        "fooqoo" || HttpStatus.OK.value()
    }

    @Unroll
    final "ユーザ送信 - postUser"() {

        given:
        final user = User.builder()
                .id(0)
                .name(name)
                .screenName("fooqoo17")
                .description("")
                .followersCount(10)
                .friendsCount(10)
                .statusesCount(10)
                .profileImageUrlHttps("http://localhost/")
                .build()

        final apiResponse = new ApiResponse(1.00, LocalDateTime.now(), user)
        userService.postUser(_ as UserRequest) >> apiResponse

        when:
        final actual = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/user")
                        .param("id", "0")
                        .param("name", name)
                        .param("screenName", "fooqoo17")
                        .param("description", "")
                        .param("followersCount", "10")
                        .param("friendsCount", "10")
                        .param("statusesCount", "10")
                        .param("profileImageUrlHttps", "http://localhost/")
        ).andReturn()

        then:
        actual.response.getStatus() == httpStatus

        where:
        name     || httpStatus
        "fooqoo" || HttpStatus.CREATED.value()
    }
}
