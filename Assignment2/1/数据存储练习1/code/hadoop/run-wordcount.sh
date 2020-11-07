#!/bin/bash


# create input directory on HDFS
hadoop fs -mkdir -p input

# put input files to HDFS
# hdfs dfs -put ./input/* input
hdfs dfs -put ./input/file1.txt input

# run wordcount 
# hadoop jar $HADOOP_HOME/share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.7.2-sources.jar org.apache.hadoop.examples.WordCount input output
hadoop jar ./jar/hadoop.jar WordCount input output

# print the output of wordcount
echo -e "\nwordcount output:"
hdfs dfs -cat output/part-r-00000 > output/output.txt
