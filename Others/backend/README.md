## DB进度

- [ ] Mysql
- [ ] Neo4j
- [ ] Hbase



## 目录结构

```
.
├── mvnw
├── mvnw.cmd
├── pom.xml                                         : maven 全局配置文件
└── src
    ├── main
    │   ├── java
    │   │   └── backend
    │   │       ├── Application.java                启动类
    │   │       ├── config                          [Config]
    |   |       │                                   : 配置类库
    │   │       ├── controller                      [Controller] 
    |   |       │                                   : 前端控制层
    │   │       ├── dto                             [Data Transfer Object]
    |   |       │                                   : 数据传输对象 : 封装多个Domain之间的关系, 不破坏Domain的结构
    │   │       ├── domain/pojo                     [Domain]
    |   |       │                                   : 实体类 : jpa-domain/mybatis-pojo
    │   │       ├── dao                             [Dao]
    |   |       │   │                               : 数据接口访问层 
    │   │       │   └── mapper                      - mybatis
    │   │       │   └── repository                  - jpa
    │   │       ├── service                         [Service] 
    |   |       │   │                               : 数据服务接口层
    │   │       │   └── impl                        [Service Implements]
    |   |       │                                   : 数据服务实现层
    │   │       ├── utils                           [Utilities]
    |   |       │                                   : 工具类库
    │   │       └── vo                              [View Object]
    |   |                                           : 视图包装对象 : 用于封装客户端请求，防止数据泄露，保证数据安全，不破坏实体类结构
    │   └── resources
    │       ├── application.yml                     : 项目配置文件
    │       ├── mapper                              : mybatis 映射文件 
    │       │   └── config                          : mybatis 配置文件
    │       ├── static                              : 静态资源目录 : html, css, js
    │       └── templates                           : 视图模版目录 : jsp, thymeleaf
    └── test 
        └── java
            └── backend                            : 单元测试目录
```

