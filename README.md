## SpringBoot1.5.2 + spring-data-elasticsearch + elasticsearch（2.2.0）
通过ElasticsearchRepository操作elasticsearch

## 版本环境说明
| 名称 | 版本 | 说明 |
| :--- | :----: | ----: |
| jdk | 1.8 |  |
|  elasticsearch    | 2.2.0      |     |
|  logstash    | 2.2.0      |     |
|  kibana    | 4.4.0      |     |
| org.elasticsearch | 2.4.4 | |
| spring-boot | 1.5.2 | |

## elk安装方法
``` sh
#下载镜像
docker pull sebp/elk:es220_l220_k440
#运行环境
docker run -d -p 5044:5044 -p 127.0.0.1:5601:5601 -p 127.0.0.1:9200:9200 -p 127.0.0.1:9300:9300  --name=elkes220_l220_k440 sebp/elk:es220_l220_k440
```

## 项目结构如下
 ``` sh
  #以下结构输出方法,输出tree到 temp.md,然后复制到文本中
 tree --dirsfirst --noreport -I README.md | sed 's/^/    /' > temp.md 
 ```
    .
    ├── doc
    ├── src
    │   └── main
    │       ├── java
    │       │   └── com
    │       │       └── twl
    │       │           └── springboot
    │       │               └── es
    │       │                   ├── controller
    │       │                   │   └── APILogStashController.java #提供api查询es数据结构
    │       │                   ├── model
    │       │                   │   └── LogStashData.java #用于对应的es所存储的字段对应的实体类
    │       │                   ├── repo
    │       │                   │   └── LogStashDataRepository.java #操作es api 方法
    │       │                   ├── scheduled
    │       │                   │   └── ScheduledLogger.java  #用于计划任务产生的日志被被logstash收集
    │       │                   ├── service
    │       │                   │   ├── impl
    │       │                   │   │   └── LogStashDataServiceImpl.java #查询业务逻辑
    │       │                   │   └── LogStashDataService.java
    │       │                   └── SpringBootESLeanApplicatoin.java # app启动入口程序
    │       └── resources
    │           ├── application-dev.properties
    │           ├── application-prod.properties
    │           ├── application.properties
    │           ├── log4j.properties
    │           └── logback-spring.xml  #主要是把logger.info这种日志输出到logstash中，关键配置
    ├── pom.xml
    └── spring-boot-es.iml
