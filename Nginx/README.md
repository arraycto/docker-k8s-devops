# Nginx快速入门

> [课程地址](https://edu.51cto.com/course/14606.html)

## 常用链接

+ [nginx下载](https://github.com/nginx/nginx/releases) 
+ [nginx官网](http://nginx.org)
+ [nginx中文网](http://www.nginx.cn/doc/)

## Nginx安装

### 本地安装

见[spring-boot-online-exam/backend/README.md#7配置nginx](https://github.com/19920625lsg/spring-boot-online-exam/blob/master/backend/README.md#7配置nginx)

### Docker安装

[docker-k8s-devops/Jenkins+K8s实现持续集成/第2章_docker基本使用.md#1搭建nginx-参考教程](https://github.com/19920625lsg/docker-k8s-devops/blob/master/Jenkins%2BK8s实现持续集成/第2章_docker基本使用.md#1搭建nginx-参考教程)

## Nginx命令选项

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

## Nginx常用命令

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
