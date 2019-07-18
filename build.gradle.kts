import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.1.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
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
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    group = "com.atto.nimontoy"
    version = "1.0.0"

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

val klaytnVersion = "1.0.0"
val springBootAdminVersion = "2.1.4"
val swaggerVersion = "3.0.0-SNAPSHOT"

project("nimontoy-core") {
    dependencies {
        compile("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
        implementation("com.klaytn.caver:core:$klaytnVersion")
    }

    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true
}

project("nimontoy-admin") {
    dependencies {
        implementation(project(":nimontoy-core"))

        implementation("de.codecentric:spring-boot-admin-starter-server:$springBootAdminVersion")
    }
}

project("nimontoy-api") {
    dependencies {
        implementation(project(":nimontoy-core"))

        implementation("de.codecentric:spring-boot-admin-starter-client:$springBootAdminVersion")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-security")

        implementation("io.springfox:springfox-swagger2:$swaggerVersion")
        implementation("io.springfox:springfox-swagger-ui:$swaggerVersion")
        implementation("io.springfox:springfox-spring-webflux:$swaggerVersion")

        implementation("org.springframework.boot:spring-boot-devtools")
    }
}