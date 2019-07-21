pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
rootProject.name = "nimontoy"

/**
 * nimontoy-core: 도메인 및 공통 사용 컴포넌트
 * nimontoy-admin: Spring Boot Admin Server
 * nimontoy-api: Spring Boot Admin Client 를 포함한 API Application
 */
include("nimontoy-core", "nimontoy-admin", "nimontoy-api")