# 第2章 docker基本使用

## docker基本架构

![docker基本架构](https://img1.mukewang.com/5cf21d7700011b3819201080.jpg)

## 安装mysql的基本命令

> docker run -p 3306:3306 --name mysql-demo1 -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=aA111111 -d mysql:5.7.15

+ `-p 本机端口:容器内端口`实现内外的端口映射，当不想自己指定时直接用`-P`来代替，会自动占用主机上的一个`高端口`(一般是32768~65536)来映射容器的端口
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

## docker build自己的镜像

> 见[docker_demo](docker_demo)

## docker的帮助查看

> 任何命令想不起来时都可以通过`--help`来查看帮助

```bash
[root@SZV1000302643 demo2]# docker stats --help

Usage:  docker stats [OPTIONS] [CONTAINER...]

Display a live stream of container(s) resource usage statistics

  -a, --all          Show all containers (default shows just running)
  --help             Print usage
  --no-stream        Disable streaming stats and only pull the first result

[root@SZV1000302643 demo2]# docker rmi --help

Usage:  docker rmi [OPTIONS] IMAGE [IMAGE...]

Remove one or more images

  -f, --force        Force removal of the image
  --help             Print usage
  --no-prune         Do not delete untagged parents
[root@SZV1000302643 demo2]#
```

## 查看容器日志

+ `docker logs 容器id`:查看瞬时的容器内的标准输出
+ `docker logs -f 容器id`:可以持续查看容器内部的标准输出(比如有些程序是持续运行地，`-f` 让 docker logs 像使用 tail -f 一样来输出容器内部的标准输出)

## 查看容器进程

> `docker top 容器id或name`

## 查看容器的详细信息

> `docker inspect 容器id或name`

## 镜像的查看和修改

> 见教程 https://www.runoob.com/docker/docker-image-usage.html

### 对修改后的容器保存为新的镜像

> docker commit -m='create index.html' -a='l00379880' 0a6389dbe443 l00379880/ubuntu:v587

各个参数说明：

+ `-m`:提交的描述信息
+ `-a`:指定镜像作者
+ `0a6389dbe443`：容器ID
+ `l00379880/ubuntu:v587`:指定要创建的目标镜像名

用docker images可以查看刚刚提交的新镜像
