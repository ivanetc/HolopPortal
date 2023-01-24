package com.example.holopportal

import com.example.holopportal.screenplay.entities.Screenplay
import com.example.holopportal.tasks.entities.TaskType
import com.example.holopportal.tasks.services.TasksService
import com.example.holopportal.tasks.views.NewTaskForm
import com.example.holopportal.user.entities.User
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
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
    NamedParameterJdbcOperations jdbcOperations

    def "should create new task"(){
        given:
        TaskType taskType = new TaskType(0)
        List<User> workers = List<User>()
        Screenplay screenplay = new Screenplay()
        def description = "Описание задачи"
        def name = "Имя задачи"
        def code = "123"
        def loveImpactValue = 1;
        def honestImpactValue = 2;
        def kindnessImpactValue = 3;

        def taskForm = new NewTaskForm(
                taskType: taskType,
                workers: workers,
                screenplay: screenplay,
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
        task.get().taskType == taskType
        task.get().workers == workers
        task.get().screenplay == screenplay
        task.get().description == description
        task.get().name == name
        task.get().code == code
        task.get().loveImpactValue == loveImpactValue
        task.get().honestImpactValue == honestImpactValue
        task.get().kindnessImpactValue == kindnessImpactValue


        def nameFromBase = jdbcOperations.queryForObject(
                "SELECT name FROM tasks WHERE id = ${user.get().id}",
                [:], String
        )
        nameFromBase == name

        cleanup:
        jdbcOperations.update("DELETE FROM tasks WHERE name = '$name'", [:])
    }
}
