package com.example.holopportal

import com.example.holopportal.screenplay.entities.Screenplay
import com.example.holopportal.screenplay.services.ScreenplayService
import com.example.holopportal.screenplay.views.ScreenplayForm
import com.example.holopportal.user.entities.User
import com.example.holopportal.user.repository.UserRepo
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
class ScreenplaySpec extends Specification{

    @Inject
    ScreenplayService screenplayService

    @Inject
    UserRepo userRepo

    @Inject
    NamedParameterJdbcOperations jdbcOperations

    def "should create new screenplay"(){
        given:
        def name = "NewScreenplay"
        def code = "123"
        def content = "Lorem ipsum"

        def screenplayForm = new ScreenplayForm(
                name: name,
                code: code,
                content: content
        )

        def currentUser = userRepo.findById(1)

        when:
        def screenplay= screenplayService.createNewScreenplay(screenplayForm, currentUser)

        then:
        screenplay.isPresent()
        screenplay.get().name == name
        screenplay.get().code == code
        screenplay.get().content == content

        def nameFromBase = jdbcOperations.queryForObject(
                "SELECT name FROM screenplays WHERE id = ${screenplay.get().id}",
                [:], String
        )
        nameFromBase == name

        cleanup:
        jdbcOperations.update("DELETE FROM screenplays WHERE name = '$name'", [:])
    }

    def "should throw 400 when data is missed"(){
        when:
        screenplayService.createNewScreenplay(null, null)

        then:
        def exception = thrown(ResponseStatusException.class)
        exception.reason == "no data provided"
        exception.status == HttpStatus.BAD_REQUEST
    }
}
