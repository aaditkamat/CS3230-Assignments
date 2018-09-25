#!/usr/bin/env bash
javac GenerateTest.java
javac IdleGame.java
for i in {1..2}
do
  java GenerateTest > test${i}.in
  java IdleGame < test${i}.in > test${i}.out
done

