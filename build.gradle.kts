import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
	kotlin("plugin.serialization").version("1.6.21")
	kotlin("plugin.jpa") version "1.6.0"
}

group = "com.koltinspring"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven("https://maven.tryformation.com/releases") {
		content {
			includeGroup("com.jillesvangurp")
		}
	}
}

dependencies {

	//web
	implementation("org.springframework.boot:spring-boot-starter-web")

	//web
	implementation("org.springframework.boot:spring-boot-starter-validation")

	//kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	//validator
	implementation("org.springframework.boot:spring-boot-starter-validation")

	//logging
	implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")

	//db
//	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springframework.boot:spring-boot-starter-jdbc")
//	implementation("mysql:mysql-connector-java")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("io.mockk:mockk:1.10.4")
	testImplementation("com.ninja-squad:springmockk:3.0.1")

	implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	implementation("com.jillesvangurp:search-client:2.0.0-RC-8")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:1.6.4")
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


sourceSets {
	test {
		java {
			setSrcDirs(listOf("src/test/intg",  "src/test/unit"))
		}
	}

	/*test {
        withConvention(KotlinSourceSet::class) {
            kotlin.srcDir(listOf("src/test/intg", "src/test/unit"))
        }
    }*/

}