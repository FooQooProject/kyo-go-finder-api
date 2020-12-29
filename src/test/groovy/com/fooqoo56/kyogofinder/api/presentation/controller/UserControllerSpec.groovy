package com.fooqoo56.kyogofinder.api.presentation.controller

import com.fooqoo56.kyogofinder.api.application.service.UserService
import com.fooqoo56.kyogofinder.api.presentation.dto.response.UserApiResponse
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

    final setup() {
        userService = Mock(UserService)
        target = new UserController(userService)
        mockMvc = MockMvcBuilders.standaloneSetup(target).build()
    }

    @Unroll
    final "ユーザ取得 - UserController"() {

        given:
        final apiResponse = new UserApiResponse(1.00, LocalDateTime.now(), users)
        userService.getUser() >> apiResponse

        when:
        final actual = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/user")
        ).andReturn()

        then:
        actual.response.getStatus() == httpStatus

        where:
        users                                 || httpStatus
        [new UserApiResponse.User(0, "taro")] || HttpStatus.OK.value()

    }
}
