# Nginx快速入门

> [课程地址](https://edu.51cto.com/course/14606.html)

## 1.常用链接

+ [nginx下载](https://github.com/nginx/nginx/releases) 
+ [nginx官网](http://nginx.org)
+ [nginx中文网](http://www.nginx.cn/doc/)

## 2.Nginx安装

### 2.1 本地安装

见[spring-boot-online-exam/backend/README.md#7配置nginx](https://github.com/19920625lsg/spring-boot-online-exam/blob/master/backend/README.md#7配置nginx)

### 2.2 Docker安装

[docker-k8s-devops/Jenkins+K8s实现持续集成/第2章_docker基本使用.md#1搭建nginx-参考教程](https://github.com/19920625lsg/docker-k8s-devops/blob/master/Jenkins%2BK8s实现持续集成/第2章_docker基本使用.md#1搭建nginx-参考教程)

## 3.Nginx命令

### 3.1 命令选项

```shell
root@cb063dc3f022:/# nginx -h
nginx version: nginx/1.17.0
Usage: nginx [-?hvVtTq] [-s signal] [-c filename] [-p prefix] [-g directives]

Options:
  -?,-h         : this help 显示帮助
  -v            : show version and exit  显示版本并退出
  -V            : show version and configure options then exit 显示版本和配置选项并退出
  -t            : test configuration and exit 校验配置文件并退出
  -T            : test configuration, dump it and exit 校验配置文件,删除并退出
  -q            : suppress non-error messages during configuration testing 在校验配置文件时，正常的打印给忽略掉
  -s signal     : send signal to a master process: stop, quit, reopen, reload 给nginx发信号，可以停止、退出、重新打开、重新加载nginx进程
  -p prefix     : set prefix path (default: /etc/nginx/) 设置nginx路径前缀
  -c filename   : set configuration file (default: /etc/nginx/nginx.conf) 设置配置文件路径
  -g directives : set global directives out of configuration file 设置独立于配置文件的指令
```

### 3.2 常用命令

```shell
netstat -ntpl | grep 80 #查看进程信息
ps aux | grep ntinx
sudo nginx #启动
sudo nginx -s stop #停止
sudo nginx -s reload #重启
sudo nginx -c /etc/nginx/nginx.conf #使用指定的配置文件启动
sudo nginx -t # 测试配置文件是否有错误
sudo nginx -v #查看版本信息
```

## 4.配置文件详解

### 4.1 最关键的配置文件`/etc/nginx/nginx.conf`

主配置文件nginx.conf，包含三部分内容：**全局配置**、**工作模式配置**、**HTTP配置**。文件默认内容如下，

```shell
user  nginx;
worker_processes  1;

error_log  /var/log/nginx/error.log warn;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;

    include /etc/nginx/conf.d/*.conf;
}
```

各个字段的解析如下：

```shell
# 1.全局配置
# 运行nginx的用户
user  nginx;
# 工作进程的数量，可以根据CPU的核心总数来设置
worker_processes  4;

# 错误日志文件的位置和输出级别
error_log  /var/log/nginx/error.log warn;
# PID文件的位置
pid        /var/run/nginx.pid;

# 2.工作模式配置
events {
    # 每个进程最大处理的连接数
    worker_connections  1024;
}

# 3.HTTP配置
http {
    # 支持的媒体类型
    include       /etc/nginx/mime.types;
    # 默认的类型
    default_type  application/octet-stream;

    # 日志格式
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';
    
    # 访问日志文件的位置
    access_log  /var/log/nginx/access.log  main;

    # 是否调用sendfile函数来输出文件
    sendfile        on;
    #tcp_nopush     on;
    
    # 连接超时时间
    keepalive_timeout  65;

    # 开启gzip压缩
    #gzip  on;
    
    # 引入外部配置文件，包含虚拟主机的设置
    include /etc/nginx/conf.d/*.conf;
}
```
