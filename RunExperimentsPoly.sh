#!/bin/bash
cd PolyBenchC-4.2.1
ALGORITHM=$1
#Poly
##2mm,3mm,correlation,jacobi-2d,doitgen,gemm,gramschmidt,heat-3d
#N
BENCH_NAME=$2
APPLICATION_NAME=$3
CLASS_NAME=$4
cd ${APPLICATION_NAME}
cd ../../
java -jar TuningSystemDarwinFixed.jar $ALGORITHM $BENCH_NAME $APPLICATION_NAME $CLASS_NAME
#./RunExperimentsFinal.sh 3 Poly 2mm N