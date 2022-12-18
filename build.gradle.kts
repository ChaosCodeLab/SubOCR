import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("net.sourceforge.tess4j:tess4j:5.4.0")
    implementation("com.github.jai-imageio:jai-imageio-core:1.4.0")
    implementation("commons-io:commons-io:2.11.0")
    implementation("net.java.dev.jna:jna:5.12.1")
    implementation("net.sourceforge.lept4j:lept4j:1.16.2")
    implementation("org.apache.pdfbox:pdfbox:2.0.26")
    implementation("org.apache.pdfbox:pdfbox-tools:2.0.26")
    implementation("org.apache.pdfbox:jbig2-imageio:3.0.4")
    implementation("org.jboss:jboss-vfs:3.2.17.Final")
    implementation("org.slf4j:slf4j-api:2.0.6")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "SubOCR"
            packageVersion = "1.0.0"
        }
    }
}
