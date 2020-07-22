# activiti 7.0 安装部署

[TOC]

## 创建activiti所需的数据库表

本地安装了MySQL 8.0.20版本，需要执行以下操作：
* 更新maven依赖到8.0.18版本
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.18</version>
</dependency>
```
* 将`activiti.cfg.xml`连接配置改为以下方式，注意`driverClassName`和`url`，username和password根据实际情况配置
```xml
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/activiti?serverTimezone=UTC" />
    <property name="username" value="root" />
    <property name="password" value="aaaaaa" />
    <property name="maxActive" value="3" />
    <property name="maxIdle" value="1" />
</bean>
```

执行`testGenTable()`

## 流程图

安装actiBPM插件，创建bpmn文件，画流程图，设置参与者
将bpmn文件后缀改为xml，然后右键Diagrams，导出为png图片
运行deployProcess()

此时观察数据库，act_re_deployment、act_re_procdef（流程定义）、act_ge_bytearray（存二进制文件）表已经发生了变化

act_ru_task 正在运行中的任务
act_hi_taskinst 任务实例。所有任务（包含待办和已办，已办会有EndTime）
act_hi_identitylink 记录了参与者信息
act_hi_actinst 已完成的活动信息
act_ru_execution 执行表