plugins {
	kotlin("jvm") version "1.9.25"
}

group = "br.com"
version = "0.0.1-SNAPSHOT"
description = "DocBr"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(8)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
