# Jeorg Kotlin Arrow Optics Crums 1

First combination that actually worked:


#### Versions

```xml
<properties>
    <java.version>17</java.version>

    <kotlin-maven-plugin.version>${kotlin.version}</kotlin-maven-plugin.version>
    <kotlin.version>1.7.22</kotlin.version>
    <arrow-optics-ksp-plugin.version>1.1.4-rc.3</arrow-optics-ksp-plugin.version>
    <kotlin-maven-symbol-processing.version>1.2</kotlin-maven-symbol-processing.version>
    <arrow.version>1.1.4-alpha.16</arrow.version>
</properties>
```

#### KSP plugin

```xml

<plugin>
    <groupId>org.jetbrains.kotlin</groupId>
    <artifactId>kotlin-maven-plugin</artifactId>
    <version>${kotlin.version}</version>
    <executions>
        <execution>
            <id>test-compile</id>
            <phase>test-compile</phase>
            <goals>
                <goal>test-compile</goal>
            </goals>
        </execution>
        <execution>
            <id>compile</id>
            <phase>compile</phase>
            <goals>
                <goal>compile</goal>
            </goals>
            <configuration>
                <sourceDirs>
                    <source>src/main/kotlin</source>
                    <source>target/generated-sources/ksp</source>
                </sourceDirs>
            </configuration>
        </execution>
    </executions>
    <configuration>
        <compilerPlugins>
            <compilerPlugin>ksp</compilerPlugin>
        </compilerPlugins>
        <jvmTarget>${java.version}</jvmTarget>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>com.dyescape</groupId>
            <artifactId>kotlin-maven-symbol-processing</artifactId>
            <version>${kotlin-maven-symbol-processing.version}</version>
        </dependency>
        <dependency>
            <groupId>io.arrow-kt</groupId>
            <artifactId>arrow-optics-ksp-plugin</artifactId>
            <version>${arrow-optics-ksp-plugin.version}</version>
        </dependency>
    </dependencies>
</plugin>
```

# References

-   [kotlin-maven-symbol-processing](https://github.com/Dyescape/kotlin-maven-symbol-processing)

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
