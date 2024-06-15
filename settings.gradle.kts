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
