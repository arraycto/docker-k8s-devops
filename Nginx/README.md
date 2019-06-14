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
