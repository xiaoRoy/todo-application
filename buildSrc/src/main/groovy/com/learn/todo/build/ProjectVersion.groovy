package com.learn.todo.build

class ProjectVersion {
    Integer major
    Integer minor
    Boolean isRelease

    ProjectVersion (Integer major, Integer minor) {
        this.major = major
        this.minor = minor
        isRelease = Boolean.FALSE
    }

    ProjectVersion (Integer major, Integer minor, Boolean isRelease) {
        this(major, minor)
        this.isRelease = isRelease
    }

    @Override
    String toString () {
        "$major.$minor${isRelease ? '' : '-SNAPSHOT'}"
    }
}
