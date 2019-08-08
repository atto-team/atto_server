import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.1.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("org.asciidoctor.convert") version "1.5.3"
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

val klaytnVersion = "1.0.0"
val springBootAdminVersion = "2.1.4"
val swaggerVersion = "3.0.0-SNAPSHOT"
val restDocsVersion = "2.0.3.RELEASE"
val restAssuredVersion = "3.0.2"
val mailVersion = "1.6.2"

project("nimontoy-core") {
    dependencies {
        compile("mysql:mysql-connector-java")
        compile("org.springframework.boot:spring-boot-starter-web")
        compile("org.springframework.boot:spring-boot-starter-data-jpa")
        
        implementation("com.sun.mail:javax.mail:$mailVersion")
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
        implementation(project(":nimontoy-security"))

        implementation("de.codecentric:spring-boot-admin-starter-client:$springBootAdminVersion")
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        asciidoctor("org.springframework.restdocs:spring-restdocs-asciidoctor:$restDocsVersion")

        testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:$restDocsVersion")
        testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
        testImplementation("org.springframework.restdocs:spring-restdocs-restassured")

        implementation("org.springframework.boot:spring-boot-devtools")
    }
}

project("nimontoy-security") {
    dependencies {
        implementation(project(":nimontoy-core"))

        implementation("org.springframework.boot:spring-boot-starter-security")
    }
}