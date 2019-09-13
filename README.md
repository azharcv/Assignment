# Assignment

Instrumentation tests coverage reports
Generating instrumentation tests code coverage reports requires a minor change to the build script.

 {
  buildTypes {
    debug {
      testCoverageEnabled true
    }
  }
}


## Testing frameworks and APIs


Espresso
Espresso is a part of the ATSL (Android Testing Support Library) and a framework for writing concise, beautiful, and reliable Android UI tests.

Espresso-Core
The core API is small, predictable, and easy to learn API which. Espresso enables testing of state expectations, interactions, and assertions clearly without the distraction of boilerplate content, custom infrastructure, or messy implementation details getting in the way.


