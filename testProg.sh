#!/bin/bash

# map phase
for f in $*; do
    export mapreduce_map_input_file=$f
    java -cp ./target/project1.jar edu.cmu.scs.cc.project1.Mapper >> mapout.$$
done
# reduce phase
LC_ALL=C sort -k1,1i mapout.$$ | java -cp ./target/project1.jar edu.cmu.scs.cc.project1.Reducer >> mapred_output
