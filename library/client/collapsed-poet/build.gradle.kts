version = "1.0"
group = "com.github.mikhailstepanov88.java-meetup"

plugins {
    id("org.gradle.java")
}

val springVersion = ext.get("versions.spring") as String
val reactorVersion = ext.get("versions.reactor") as String
val javapoetVersion = ext.get("versions.javapoet") as String

dependencies {
    compile(project(":library:data"))
    compile(project(":library:client:common"))

    compile(group = "com.squareup", name = "javapoet", version = javapoetVersion)

    compileOnly(group = "io.projectreactor", name = "reactor-core", version = reactorVersion)
    compileOnly(group = "org.springframework", name = "spring-webflux", version = springVersion)
}