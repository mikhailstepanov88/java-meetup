version = "1.0"
group = "com.github.mikhailstepanov88.java-meetup"

plugins {
    id("org.gradle.java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

val lombokVersion = ext.get("versions.lombok") as String

dependencies {
    compile(project(":library:data"))
    compile(project(":library:converter"))

    compile(group = "org.springframework.boot", name = "spring-boot-starter-json")
    compile(group = "org.springframework.boot", name = "spring-boot-starter-webflux")
    compile(group = "org.springframework.boot", name = "spring-boot-starter-data-mongodb-reactive")

    annotationProcessor(group = "org.projectlombok", name = "lombok", version = lombokVersion)
    annotationProcessor(group = "org.springframework.boot", name = "spring-boot-configuration-processor")
}

springBoot {
    mainClassName = "com.github.mikhailstepanov88.java_meetup.idea.IdeaServiceApplication"
}