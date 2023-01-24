package com.example.holopportal

import com.example.holopportal.user.services.UserService
import com.example.holopportal.user.views.UserForm
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.inject.Inject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

//@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = HolopPortalApplication.class)
@SpringBootTest(
        webEnvironment = NONE,
        classes = HolopPortalApplication
)
class UserSpec extends Specification {

    @Inject
    UserService userService

    @Inject
    NamedParameterJdbcOperations jdbcOperations

    def "should create new user"() {
        given:
        def firstName = "Alexander"
        def lastName = "Ivanets"
        def login = "testUser1"
        def password = "123"

        def userForm = new UserForm(
                firstName: firstName,
                lastName: lastName,
                login: login,
                password: password
        )

        when:
        def user = userService.createNewUser(userForm)

        then:
        user.isPresent()
        user.get().firstName == firstName
        user.get().lastName == lastName
        user.get().login == login

        def loginFromBase = jdbcOperations.queryForObject(
                "SELECT login FROM users WHERE id = ${user.get().id}",
                [:], String
        )
        loginFromBase == login

        cleanup:
        jdbcOperations.update("DELETE FROM users WHERE login = '$login'", [:])
    }
}