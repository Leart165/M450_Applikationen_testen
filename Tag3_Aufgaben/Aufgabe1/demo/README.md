```bash
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------------------< main:demo >------------------------------
[INFO] Building demo 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ demo ---
[INFO] Deleting /Users/tim/Projekte/M450_Applikationen_testen/Tag3_Aufgaben/Aufgabe1/demo/target
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ demo ---
[INFO] skip non existing resourceDirectory /Users/tim/Projekte/M450_Applikationen_testen/Tag3_Aufgaben/Aufgabe1/demo/src/main/resources
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ demo ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 1 source file with javac [debug release 17] to target/classes
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ demo ---
[INFO] skip non existing resourceDirectory /Users/tim/Projekte/M450_Applikationen_testen/Tag3_Aufgaben/Aufgabe1/demo/src/test/resources
[INFO] 
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ demo ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 1 source file with javac [debug release 17] to target/test-classes
[INFO] 
[INFO] --- surefire:3.2.5:test (default-test) @ demo ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running test.CalculatorTest
[INFO] Tests run: 13, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.037 s -- in test.CalculatorTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 13, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.258 s
[INFO] Finished at: 2025-09-05T09:28:19+02:00
[INFO] ------------------------------------------------------------------------

```