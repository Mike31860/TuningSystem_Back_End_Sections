#!/bin/bash
cd SNU_NPB-1.0.3/NPB3.3-SER-C
ALGORITHM=$1
BENCH_NAME=$2
CLASS_NAME=$3
cd ${BENCH_NAME}
make CLASS=${CLASS_NAME}
cd ../../../
java -jar TuningSystemDarwinFixed.jar $ALGORITHM $BENCH_NAME $CLASS_NAME