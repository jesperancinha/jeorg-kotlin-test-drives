plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
}
rootProject.name = "jeorg-kotlin-test-drives-gradle"
include("jeorg-kotlin-arrow-optics")
include("jeorg-kotlin-apps:jeorg-microchip-maker:jeorg-microchip-maker-gui")
include("jeorg-kotlin-arrow-optics:jeorg-kotlin-optics-crums-2")
include("jeorg-kotlin-arrow-optics:jeorg-kotlin-optics-crums-1")
include("experiments")
include("experiments:sealed-interfaces-interops")
include("jeorg-kotlin-utilities:kotlin-number-operations")
include("jeorg-kotlin-utilities:kotlin-string-operations")
findProject(":experiments:sealed-interfaces-interops")?.name = "sealed-interfaces-interops"
include("jeorg-kotlin-apps:monads-are-no-nomads")
findProject(":jeorg-kotlin-apps:monads-are-no-nomads")?.name = "monads-are-no-nomads"
include("jeorg-kotlin-crums:module-common")
findProject(":jeorg-kotlin-crums:module-common")?.name = "module-common"
include("jeorg-kotlin-crums:module-use")
findProject(":jeorg-kotlin-crums:module-use")?.name = "module-use"
include("jeorg-kotlin-apps:json-to-builder-pattern")
findProject(":jeorg-kotlin-apps:json-to-builder-pattern")?.name = "json-to-builder-pattern"
include("jeorg-kotlin-apps:xml-adder")
findProject(":jeorg-kotlin-apps:xml-adder")?.name = "xml-adder"
include("jeorg-kotlin-apps:performance-projects:performance-input-test-generator")
include("jeorg-kotlin-apps:performance-projects:performance-buffered-reader")
include("jeorg-kotlin-apps:performance-projects:performance-jump-search")
