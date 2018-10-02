package com.learn.todo.build

import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionGraph
import org.gradle.api.execution.TaskExecutionGraphListener

/*
* Keep in mind that you don’t have direct access to the Project
* */
class ReleaseVersionListener implements TaskExecutionGraphListener {

    final static String RELEASE_TASK_PATH = ':release'

    @Override
    void graphPopulated (TaskExecutionGraph graph) {
        if (graph.hasTask(RELEASE_TASK_PATH)) {
            List<Task> allTasks = graph.getAllTasks()
            def releaseTask = allTasks.find { it.path == RELEASE_TASK_PATH }
            def project = releaseTask.project
            if (!project.version.isRelease) {
                project.version.isRelease = true
                project.ant.propertyfile(file: project.versionFile) {
                    entry(key: 'isRelease', type: 'string', operation: '=', value: 'true')
                }
            }
        }
    }
}
