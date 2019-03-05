buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.1.3.RELEASE")
    }
}

allprojects {
    repositories {
        jcenter()
    }

    ext {
        set("versions.lombok", "1.18.6")
        set("versions.immutable", "2.7.4")
    }
}