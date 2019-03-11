version = "1.0"
group = "com.github.mikhailstepanov88.java-meetup"

plugins {
    id("org.gradle.java")
}

val reactorVersion = ext.get("versions.reactor") as String

dependencies {
    compile(project(":library:data"))
    compile(project(":library:client:common"))

    compile(group = "com.squareup.retrofit2", name = "retrofit", version = "2.5.0")
    compile(group = "com.squareup.retrofit2", name = "converter-jackson", version = "2.5.0") {
        exclude(group = "com.fasterxml.jackson.core")
    }
    
    compileOnly(group = "io.projectreactor", name = "reactor-core", version = reactorVersion)
}