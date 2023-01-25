package com.example.holopportal

import com.example.holopportal.screenplay.entities.Screenplay
import com.example.holopportal.screenplay.entities.ScreenplayStatus
import com.example.holopportal.screenplay.repository.ScreenplayRepo
import com.example.holopportal.tasks.entities.TaskType
import com.example.holopportal.tasks.repository.TaskRepo
import com.example.holopportal.tasks.repository.TaskTypeRepo
import com.example.holopportal.tasks.services.TasksService
import com.example.holopportal.tasks.views.NewTaskForm
import com.example.holopportal.user.entities.User
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

class TaskSpec extends Specification{

    @Inject
    TasksService tasksService

    @Inject
    TaskTypeRepo taskTypeRepo

    @Inject
    ScreenplayRepo screenplayRepo

    @Inject
    NamedParameterJdbcOperations jdbcOperations

    def "should create new task"() {
        given:
        def taskType = taskTypeRepo.findById(1)
        List<User> workers = Collections.emptyList()
        def screenplay = screenplayRepo.findById(1)
        def description = "Описание задачи"
        def name = "Имя задачи test 1"
        def code = "123"
        def loveImpactValue = 1;
        def honestImpactValue = 2;
        def kindnessImpactValue = 3;

        def taskForm = new NewTaskForm(
                taskType: taskType.get(),
                workers: workers,
                screenplay: screenplay.get(),
                description:description,
                name: name,
                code: code,
                loveImpactValue: loveImpactValue,
                honestImpactValue: honestImpactValue,
                kindnessImpactValue: kindnessImpactValue
        )

        when:
        def task = tasksService.createTask(taskForm)

        then:
        task.get().taskType == taskType.get()
        task.get().workers == workers
        task.get().screenplay == screenplay.get()
        task.get().description == description
        task.get().name == name
        task.get().code == code
        task.get().loveImpactValue == loveImpactValue
        task.get().honestImpactValue == honestImpactValue
        task.get().kindnessImpactValue == kindnessImpactValue


        def nameFromBase = jdbcOperations.queryForObject(
                "SELECT name FROM tasks WHERE id = ${task.get().id}",
                [:], String
        )
        nameFromBase == name

        cleanup:
        jdbcOperations.update("DELETE FROM tasks WHERE name = '$name'", [:])
    }
        def "should throw 400 when data is missed"(){
        when:
        tasksService.createTask(null)

        then:
        def exception = thrown(ResponseStatusException.class)
        exception.reason == "no data provided"
        exception.status == HttpStatus.BAD_REQUEST
    }
}
