import org.springframework.boot.gradle.tasks.bundling.BootJar

buildscript {
    extra["kotlinVersion"] = "1.3.30"
    extra["springBootVersion"] = "2.1.7.RELEASE"

    repositories {
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlinVersion"]}")
        classpath("org.jetbrains.kotlin:kotlin-allopen:${extra["kotlinVersion"]}") // kotlin-spring 사용을 위해 필요하다.
        /**
         * <a href="https://kotlinlang.org/docs/reference/compiler-plugins.html#no-arg-compiler-plugin">No-arg compiler plugin</a>
         */
        classpath("org.jetbrains.kotlin:kotlin-noarg:${extra["kotlinVersion"]}") // kotlin-jpa 사용을 위해 필요하다.
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${extra["springBootVersion"]}")
    }
}

plugins {
    id("org.springframework.boot") version "2.1.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("org.asciidoctor.convert") version "1.5.3"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.2.71"

    kotlin("jvm") version "1.2.71"
    kotlin("plugin.spring") version "1.2.71"
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
        maven(url = "http://oss.jfrog.org/artifactory/oss-snapshot-local/")
    }
}

subprojects {
    extra["klaytnVersion"] = "1.0.0"
    extra["springBootAdminVersion"] = "2.1.4"
    extra["swaggerVersion"] = "3.0.0-SNAPSHOT"
    extra["restDocsVersion"] = "2.0.3.RELEASE"
    extra["restAssuredVersion"] = "3.0.2"
    extra["mailVersion"] = "1.6.2"
    extra["coroutinesCoreVersion"] = "1.3.0-RC2"
    extra["firebaseAdminVersion"] = "6.8.1"
    extra["swaggerVersion"] = "2.9.2"

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.asciidoctor.convert")

    group = "com.atto.nimontoy"
    version = "1.0.0"

    ext {
        set("snippetsDir", file("build/generated-snippets"))
    }

    tasks.test {
        outputs.dir(ext.get("snippetsDir")!!)
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
            dependsOn(processResources)
        }

        compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
        }
    }
}

project("nimontoy-core") {
    dependencies {
        compile("mysql:mysql-connector-java")
        compile("org.springframework.boot:spring-boot-starter-web")
        compile("org.springframework.boot:spring-boot-starter-data-jpa")
        compile("org.springframework.boot:spring-boot-starter-data-redis")

        // coroutines core
        compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:${extra["coroutinesCoreVersion"]}")

        implementation("com.sun.mail:javax.mail:${extra["mailVersion"]}")
        implementation("com.klaytn.caver:core:${extra["klaytnVersion"]}")
        implementation("io.springfox:springfox-swagger2:${extra["swaggerVersion"]}")
        implementation("io.springfox:springfox-swagger-ui:${extra["swaggerVersion"]}")
    }

    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true
}

project("nimontoy-admin") {
    dependencies {
        implementation(project(":nimontoy-core"))

        implementation("de.codecentric:spring-boot-admin-starter-server:${extra["springBootAdminVersion"]}")
    }
}

project("nimontoy-api") {
    dependencies {
        implementation(project(":nimontoy-core"))
        implementation(project(":nimontoy-security"))

        implementation("de.codecentric:spring-boot-admin-starter-client:${extra["springBootAdminVersion"]}")
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        asciidoctor("org.springframework.restdocs:spring-restdocs-asciidoctor:${extra["restDocsVersion"]}")

        testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:${extra["restDocsVersion"]}")
        testImplementation("io.rest-assured:rest-assured:${extra["restAssuredVersion"]}")
        testImplementation("org.springframework.restdocs:spring-restdocs-restassured")

        implementation("org.springframework.boot:spring-boot-devtools")
    }
}

project("nimontoy-security") {
    dependencies {
        implementation(project(":nimontoy-core"))

        implementation("com.google.firebase:firebase-admin:${extra["firebaseAdminVersion"]}")
        implementation("org.springframework.boot:spring-boot-starter-security")
    }
}