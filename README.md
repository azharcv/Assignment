# Assignment

Instrumentation tests coverage reports
Generating instrumentation tests code coverage reports requires a minor change to the build script.

android {
  buildTypes {
    debug {
      testCoverageEnabled true
    }
  }
}
