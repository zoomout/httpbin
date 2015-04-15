To run sequential:
mvn clean compile test -Dsuite=RunSequential

To run in parallel:
mvn clean compile test -Dsuite=RunParallel

Warning! Test fails because it's buggy: HttpBinResponseHeadersTest.testResponseHeadersInParameters3