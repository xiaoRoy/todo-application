package com.learn.todo.build

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

class ReleaseVersionTask extends DefaultTask {

    @Input
    Boolean isRelease
    @OutputFile
    File versionFile

    ReleaseVersionTask () {
        group = 'version info'
        description = 'Make project a release version'
    }


    @TaskAction
    void start () {
        project.version.isRelease = true
        ant.propertyfile(file: versionFile){
            entry(key: 'release', type: 'string', operation: '=', value: 'true')
        }
    }
}
