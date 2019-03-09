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
        set("versions.jackson", "2.9.0")
        set("versions.immutable", "2.7.4")
        set("versions.spring", "5.1.5.RELEASE")
        set("versions.reactor", "3.2.6.RELEASE")
        set("versions.spring-boot", "2.1.3.RELEASE")
    }
}