#!/bin/bash

# List of modules to update
MODULES=(
  "/home/jesperancinha/dev/src/jesperancinha/jeorg-kotlin-test-drives/jeorg-kotlin-apps/string-array-paradigms/string-paradigm-api/build.gradle.kts"
  "/home/jesperancinha/dev/src/jesperancinha/jeorg-kotlin-test-drives/jeorg-kotlin-apps/string-array-paradigms/string-paradigm-expression-no-wrapper/build.gradle.kts"
  "/home/jesperancinha/dev/src/jesperancinha/jeorg-kotlin-test-drives/jeorg-kotlin-apps/string-array-paradigms/string-paradigm-expression-original/build.gradle.kts"
  "/home/jesperancinha/dev/src/jesperancinha/jeorg-kotlin-test-drives/jeorg-kotlin-apps/string-array-paradigms/string-paradigm-expression_1/build.gradle.kts"
  "/home/jesperancinha/dev/src/jesperancinha/jeorg-kotlin-test-drives/jeorg-kotlin-apps/string-array-paradigms/string-paradigm-expression_2/build.gradle.kts"
  "/home/jesperancinha/dev/src/jesperancinha/jeorg-kotlin-test-drives/jeorg-kotlin-apps/string-array-paradigms/string-paradigm-no-wrapper/build.gradle.kts"
  "/home/jesperancinha/dev/src/jesperancinha/jeorg-kotlin-test-drives/jeorg-kotlin-apps/string-array-paradigms/string-paradigm-test-generator/build.gradle.kts"
  "/home/jesperancinha/dev/src/jesperancinha/jeorg-kotlin-test-drives/jeorg-kotlin-apps/string-array-paradigms/string-paradigm_1/build.gradle.kts"
  "/home/jesperancinha/dev/src/jesperancinha/jeorg-kotlin-test-drives/jeorg-kotlin-apps/string-array-paradigms/string-paradigm_original/build.gradle.kts"
)

for module in "${MODULES[@]}"; do
  echo "Processing $module"
  # Check if the file contains the pattern we want to replace
  if grep -q 'testImplementation("org.junit.jupiter:junit-jupiter-api")' "$module" && \
     grep -q 'testImplementation("org.junit.jupiter:junit-jupiter-engine")' "$module"; then
    
    # Use sed to replace the pattern
    sed -i 's/testImplementation("org.junit.jupiter:junit-jupiter-api")/testImplementation(libs.junit.jupiter.api)/g' "$module"
    sed -i 's/testImplementation("org.junit.jupiter:junit-jupiter-engine")/testImplementation(libs.junit.jupiter.engine)/g' "$module"
    
    echo "Updated $module"
  else
    echo "Pattern not found in $module"
  fi
done

echo "All modules updated"