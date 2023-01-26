package com.example.holopportal

import com.example.holopportal.project.services.ProjectService
import com.example.holopportal.project.views.NewProjectForm
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
class ProjectSpec extends Specification {

    @Inject
    ProjectService projectService

    @Inject
    NamedParameterJdbcOperations jdbcOperations

    def "should create new user"() {
        given:
        def firstName = "Ivan"
        def lastName = "Ivanov"
        def age = 19
        def wishes = "Lorem ipsum"
        def honestImpactValue = 10
        def kindnessImpactValue = 15
        def loveImpactValue = 12

        def projectForm = new NewProjectForm(
                firstName: firstName,
                lastName: lastName,
                age: age,
                wishes: wishes,
                honestImpactValue: honestImpactValue,
                kindnessImpactValue: kindnessImpactValue,
                loveImpactValue: loveImpactValue
        )

        when:
        def project = projectService.createProject(NewProjectForm)

        then:
        project.isPresent()
        project.get().firstName == firstName
        project.get().lastName == lastName
        project.get().age == age
        project.get().wishes == wishes
        project.get().honestImpactValue == honestImpactValue
        project.get().kindnessImpactValue == kindnessImpactValue
        project.get().loveImpactValue == loveImpactValue

        def wishesFromBase = jdbcOperations.queryForObject(
                "SELECT wishes FROM project WHERE id = ${project.get().id}",
                [:], String
        )
        wishesFromBase == wishes

        cleanup:
        jdbcOperations.update("DELETE FROM project WHERE wishes = '$wishes'", [:])
    }
}