# 第1章 课程前言

## 1.1 课程介绍

### 收获1：教你实战

+ 数十个典型的Nginx配置场景
  + 代理服务
  + 动态缓存
  + 动静分离
  + 负载均衡
  + Nginx和Lua的开发
+ 覆盖了90%以上的Nginx核心配置模块
+ 基于企业中常见的业务配置场景

### 收获2：了解中间件架构

+ Nginx应用层的安全防护
+ 基于Nginx的中间件架构性能优化的问题
  + 对sql的注入防攻击
  + 对请求的访问控制
  + 对请求的频率控制
  + 防爬虫

### 收获3：Nginx中间价性能优化

+ http性能压测
+ 性能瓶颈分析
+ 系统性能优化
+ 基于Nginx的性能配置优化

### 课程结构

+ 基础篇
  + 快速安装
  + 配置语法
  + 默认模块
  + Nginx的log
  + 访问限制
    + HTTP的请求和连接
    + 请求限制和连接限制
    + access的模块配置语法
    + 请求限制局限性
    + 基本安全认证
    + auth模块配置语法
    + 安全认证局限性

+ 场景实践篇
  + 静态资源Web服务
    + 什么是静态资源
    + 静态资源服务场景
    + 静态资源服务配置
    + 客户端缓存
    + 静态资源压缩
    + 放盗链
    + 跨域访问
  + 代理服务
  + 负载均衡
  + 缓存服务

+ 深度学习篇
  + 动静分离
  + rewrite规则
  + 进阶模块配置
  + HTTPS服务
    + HTTPS协议
    + 配置语法
    + Nginx的Https服务
    + 苹果要求的Https服务
  + Nginx与Lua开发

+ 架构篇
  + 常见问题
  + Nginx中间件性能优化
    + 如何调试性能优化
    + 如何优化影响因素
    + 操作系统性能优化
    + Nginx性能优化
  + Nginx与安全
  + 新版本特性
  + 中间件架构设计

## 学习环境准备

> 操作系统是CentOS7.2 安装教程可以参考 [spring-boot-online-exam/backend/README.md#7配置nginx](https://github.com/19920625lsg/spring-boot-online-exam/blob/master/backend/README.md#7配置nginx)

+ 1、两项安装
  ```shell
  yum -y install gcc gcc-c++ autoconf pcre pcre-devel make automake 
  yum -y install wget httpd-tools vim
  ```

+ 2、创建文件夹
  ```shell
  cd /opt
  mkdir app download logs work backup
  ```

+ 3、关闭iptables
  ```shell
  iptables -L # 查看
  iptables -F # 关闭
  # 保险起见
  iptables -t nat -L
  iptables -t nat -F
  ```
+ 4、停用selinux
  ```shell
  getenforce  
  # 如果不是Disabled
  setenforce 0
  ```


