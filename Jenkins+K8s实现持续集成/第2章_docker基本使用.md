# 第2章 docker基本使用

## docker基本架构

![docker基本架构](https://img1.mukewang.com/5cf21d7700011b3819201080.jpg)

## 安装mysql的基本命令

> docker run -p 3306:3306 --name mysql-demo1 -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=aA111111 -d mysql:5.7.15

+ `-e`:Enviroment环境变量
+ `-d`:daemon 后台运行
+ `-v`:volume 存储卷

## 登入与登出


### docker login 

> 登陆到一个Docker镜像仓库，如果未指定镜像仓库地址，默认为官方仓库 Docker Hub, 格式`docker login [OPTIONS] [SERVER]`


### docker logout

> 登出一个Docker镜像仓库，如果未指定镜像仓库地址，默认为官方仓库 Docker Hub `docker logout [OPTIONS] [SERVER]`


### OPTIONS说明：

+ `-u` :登陆的用户名
+ `-p` :登陆的密码

### 示例

登录dockerhub

```shell
docker login -u 用户名 -p 密码
```

登录阿里云docker仓库

```shell
docker login -u 用户名 -p 密码 registry.cn-hangzhou.aliyuncs.com
```

### 退出dockerhub

```shell
docker logout
```






