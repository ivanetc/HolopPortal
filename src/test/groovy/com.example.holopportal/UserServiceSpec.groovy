package com.example.holopportal

import com.example.holopportal.user.services.UserService
import com.example.holopportal.user.views.UserForm
import org.junit.Before
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

import javax.inject.Inject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@SpringBootTest(
        webEnvironment = NONE,
        classes = HolopPortalApplication
)
class UserServiceSpec extends Specification {

    @Inject
    UserService userService

    @Inject
    NamedParameterJdbcOperations jdbcOperations

    def setup() {
        println "This method is executed before each specification"
    }

    def setupSpec() {
        println "This method is executed only one time before all other specifications"
    }

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

    def "should throw 400 when data is missed"(){
        when:
        userService.createNewUser(null)

        then:
        def exception = thrown(ResponseStatusException.class)
        exception.reason == "no data provided"
        exception.status == HttpStatus.BAD_REQUEST
    }
}