# 第2章 docker基本使用

## docker基本架构

![docker基本架构](https://img1.mukewang.com/5cf21d7700011b3819201080.jpg)

## 安装mysql的基本命令

> docker run -p 3306:3306 --name mysql-demo1 -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=aA111111 -d mysql:5.7.15
