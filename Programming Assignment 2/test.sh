#!/usr/bin/env bash

javac Lectures.java
java Lectures < sampleInputA.txt > generatedOutputA.txt
java Lectures < sampleInputB.txt > generatedOutputB.txt
java Lectures < sampleInputC.txt > generatedOutputC.txt
java Lectures < sampleInputD.txt > generatedOutputD.txt
rm *.class

diff generatedOutputA.txt sampleOutputA.txt
diff generatedOutputB.txt sampleOutputB.txt
diff generatedOutputC.txt sampleOutputC.txt
diff generatedOutputD.txt sampleOutputD.txt