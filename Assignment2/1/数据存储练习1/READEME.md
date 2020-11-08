# 文件夹结构

```
├── document
│   └── document.md  本次作业具体的实现流程,包括Hadoop的搭建及使用,以及词性还原判断规则文档
└── result
    └── result.txt 最终的单词词频统计降序排序结果
├── READEME.md 本文档
├── code 
    ├── hadoop 对docker里的Hadoop容器目录映射的文件夹(即对Hadoop常用的操作文件)
    │   ├── input
    │   │   └── file1.txt 已经用Hadoop处理过一次的词频统计文件(单词字典序排序)
    │   ├── jar
    │   │   ├── hadoop1.jar 该jar包用于第一次词频统计(单词字典序排序)
    │   │   └── hadoop2.jar 该jar包用于第二次词频统计(单词出现频率排序)
    │   ├── output
    │   │   ├── output0.txt 词频统计文件(单词字典序排序)
    │   │   ├── output1.txt 对output0进行简单筛选处理的文件
    │   │   ├── output2.txt 对output1进行Hadoop第二次词频统计文件(单词出现频率排序)
    │   │   └── result.txt 对output2进一步处理得到的最终的单词词频统计降序排序结果
    │   └── run-wordcount.sh shell脚本,控制Hadoop的输入输出,以及使用的jar包等
    
    ├── hadoop-cluster-docker Github找到的开源Hadoop集群docker容器配置
    │   ├── config
    │   │   └── yarn-site.xml  设置一些Hadoop资源分配信息,如内存大小等
    │   └── start-container.sh 设置一些自定义的启动容器的容器卷信息
    
    └── Java_code 存放处理数据的Java代码以及Hadoop的词频统计代码
        ├── NLPProcessing.java 尝试使用NLP处理词性还原,太慢,弃用
        ├── ResultProcessing.java 对Hadoop的输出结果进行进一步处理所使用的Java类
        ├── StandardProcessing.java 对文本进行初步划分筛选使用的Java类
        ├── WordCountNLP.java 包含NLP的Hadoop词频统计Java类,因为太慢,也弃用
        ├── WordCountSimple.java 对初步处理的以空格区分的单词文本进行第一次词频统计(单词字典序排序)
        └── WordCountSort.java 对Hadoop统计好的词频按频率降序排列用的Hadoop MapReduce

```

