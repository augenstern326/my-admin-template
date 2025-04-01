<div align="center"><b>my-admin-template(粗记录)</b></div>





动态路由实现了，但是问题很多，不是正确的实现方法，很多地方都写死了，强行实现了效果，后面再优化；

虽然支持创建不同角色，但是目前一个人只能拥有一个角色，十分不合理。



### **在线体验**

前端


|用户名|密码|角色|菜单栏显示|
|-|-|-|-|
|admin|123456|admin|首页、信息系统、Markdown、项目源码、接口文档、druid监控、用户管理、角色管理|
|xiaoming|123456|user|首页、信息系统、Markdown、项目源码|



### **使用技术**

前后端分离

前端：vue 、ant-design-vue、webpack

后端：springboot、mybatis-plus、shiro、jwt

其他：lombok、swagger(knife4j)、oshi、mavonEditor

构建：Maven、jdk8

### **启动**

1、进入vue文件夹，启动前端

```
npm install
npm start
```

2、打开```springboot/main/resources/SQL```,执行create.sql创建数据库和数据表(未调整，暂时不要使用)

3、打开```springboot/main/resources/application.yml```

修改

```
spring:
  datasource:
    url: jdbc:mysql:"xxx.xxx.xxx.xxx"
    username: root
    password: xxxxxx
    driver-class-name: com.mysql.cj.jdbc.Driver
```

4、Maven安装依赖

5、点击StartApplication运行

### **项目结构**

前端:

vue

后端:

springboot

数据库语句:

springboot/main/resources/SQL

### **项目功能**


（1）接口文档

（2）系统监控

（3）markdown编辑器

（4）用户管理

（5）角色管理


### **参考和感谢**

[JeecgBoot开发文档](http://doc.jeecg.com/2043868)

[Ant Design VUe](https://www.antdv.com/docs/vue/introduce-cn/)

[vue-element-admin](https://github.com/PanJiaChen/vue-element-admin)
