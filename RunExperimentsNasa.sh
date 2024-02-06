#!/bin/bash
ALGORITHM=$1
#Poly
##2mm,3mm,correlation,jacobi-2d,doitgen,gemm,gramschmidt,heat-3d
#N
BENCH_NAME=$2
APPLICATION_NAME=$3
CLASS_NAME=$4
TUNING_FLAG=$5
LOOP_BEGINING=$6
WINDOWS_SIZE=$7

if [[ "${BENCH_NAME}" == "Nasa" ]];
    then
        cd SNU_NPB-1.0.3/NPB3.3-SER-C
        cd ${APPLICATION_NAME}
        make CLASS=${CLASS_NAME}
        cd ../../../
fi        
java -jar TS_test.jar $ALGORITHM $BENCH_NAME $APPLICATION_NAME $CLASS_NAME $TUNING_FLAG $LOOP_BEGINING $WINDOWS_SIZE
#./RunExperimentsFinal.sh 3 Poly 2mm N