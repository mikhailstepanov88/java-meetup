version = "1.0"
group = "com.github.mikhailstepanov88.java-meetup"

plugins {
    id("org.gradle.java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    compile(group = "org.springframework.boot", name = "spring-boot-starter-json")
    compile(group = "org.springframework.boot", name = "spring-boot-starter-webflux")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

springBoot {
    mainClassName = "com.github.mikhailstepanov88.java_meetup.idea.IdeaServiceApplication"
}