version = "1.0"
group = "com.github.mikhailstepanov88.java-meetup"

plugins {
    id("org.gradle.java")
}

val lombokVersion = ext.get("versions.lombok") as String
val reactorVersion = ext.get("versions.reactor") as String
val jacksonVersion = ext.get("versions.jackson") as String
val immutablesVersion = ext.get("versions.immutable") as String
val springBootVersion = ext.get("versions.spring-boot") as String

dependencies {
    compileOnly(group = "org.projectlombok", name = "lombok", version = lombokVersion)
    compileOnly(group = "org.immutables", name = "value", version = immutablesVersion)

    compileOnly(group = "io.projectreactor", name = "reactor-core", version = reactorVersion)
    compileOnly(group = "com.fasterxml.jackson.core", name = "jackson-annotations", version = jacksonVersion)
    compileOnly(group = "org.springframework.data", name = "spring-data-mongodb", version = springBootVersion)

    annotationProcessor(group = "org.projectlombok", name = "lombok", version = lombokVersion)
}