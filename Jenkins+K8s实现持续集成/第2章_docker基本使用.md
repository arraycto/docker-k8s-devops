# 第2章 docker基本使用

## docker基本架构

![docker基本架构](https://img1.mukewang.com/5cf21d7700011b3819201080.jpg)

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

## Docker搭建常用的开发环境

### 1.搭建nginx [参考教程](https://www.runoob.com/docker/docker-install-nginx.html)

`$ docker run -d -p 8082:80 --name runoob-nginx-test-web -v ~/nginx/www:/usr/share/nginx/html -v ~/nginx/conf/nginx.conf:/etc/nginx/nginx.conf -v ~/nginx/logs:/var/log/nginx nginx`

命令说明：

+ `-p 8082:80`： 将容器的 80 端口映射到主机的 8082 端口
+ `--name runoob-nginx-test-web`：将容器命名为 runoob-nginx-test-web。
+ `~/nginx/www:/usr/share/nginx/html`：将我们自己创建的 www 目录挂载到容器的 **/usr/share/nginx/html**，这个是nginx的对外访问目录
+ `-v ~/nginx/conf/nginx.conf:/etc/nginx/nginx.conf`：将我们自己创建的 nginx.conf 挂载到容器的 /etc/nginx/nginx.conf。
+ `-v ~/nginx/logs:/var/log/nginx`：将我们自己创建的 logs 挂载到容器的 /var/log/nginx。

启动以上命令后进入 ~/nginx/www 目录 `$ cd ~/nginx/www`,创建index.html,内容如下：

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜鸟教程(runoob.com)</title>
</head>
<body>
    <h1>我的第一个标题</h1>
    <p>我的第一个段落。</p>
</body>
</html>
```

然后访问`本机ip:8082`即可访问刚刚创建的index.html了

![index.html](https://www.runoob.com/wp-content/uploads/2016/06/B0DFB2A6-E1B5-4502-8EC4-0687A7C880FA.jpg)

### 2.## 安装mysql

> docker run -p 3306:3306 --name mysql-demo1 -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=aA111111 -d mysql:5.7.15

+ `-p 本机端口:容器内端口`实现内外的端口映射，当不想自己指定时直接用`-P`来代替，会自动占用主机上的一个`高端口`(一般是32768~65536)来映射容器的端口
+ `-e`:Enviroment环境变量
+ `-d`:daemon 后台运行
+ `-v`:volume 存储卷

安装mysql的8.x版本需要额外加权限

```Dockerfile
# docker 中下载 mysql
docker pull mysql

#启动
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=Lzslov123! -d mysql

#进入容器
docker exec -it mysql bash

#登录mysql
mysql -u root -p
ALTER USER 'root'@'localhost' IDENTIFIED BY 'Lzslov123!';

#添加远程登录用户
CREATE USER 'liaozesong'@'%' IDENTIFIED WITH mysql_native_password BY 'Lzslov123!';
GRANT ALL PRIVILEGES ON *.* TO 'liaozesong'@'%';
```
