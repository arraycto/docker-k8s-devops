# 第4章 搭建MySQL集群

## 4.1 MySQL集群方案介绍

### 常见的mysql集群方案

+ **Replication**：速度快弱一致性低价值适合保存日志新闻和帖子
+ **PXC**：Percona XtraDB Cluster,速度慢，强一致性，高价值适合保存订单账户财务信息。**推荐用此方案**

![常见的mysql集群方案](https://img.mukewang.com/szimg/5cf8b5fc0001881c19201080.jpg)

### PXC原理

![PXC原理](https://img.mukewang.com/szimg/5cf8c27f000177c719201080.jpg)

### PXC方案与Replication方案的对比

+ PXC方案示意图
  ![PXC方案示意图](https://img.mukewang.com/szimg/5cf8c38b0001aa2719201080.jpg)

+ Replication方案对比
  ![Replication方案示意图](https://img.mukewang.com/szimg/5cf8c438000114be19201080.jpg)

### PXC方案的数据强一致性

+ **PXC**：**同步复制**,事务在所有集群节点要么同时提交，要么不提交
  ![同步复制](https://img.mukewang.com/szimg/5cf8c52f0001e98719201080.jpg)
+ **Replication**：**异步复制**，无法保证数据的一致性
  ![异步复制](https://img.mukewang.com/szimg/5cf8c5e00001d74719201080.jpg)

### PXC方案强一致性的案例

> 图中的节点被认为关闭防火墙，直接与数据库其他节点同步，会发现此时这个节点写入数据会直接报错~如下图

![PXC方案的数据强一致性](https://img.mukewang.com/szimg/5cf8c74b0001166819201080.jpg)

## 4-2 创建PXC的MySQL集群

### 镜像地址

[percona/percona-xtradb-cluster](https://hub.docker.com/r/percona/percona-xtradb-cluster)

### 用到的命令

+ 安装：
  + `docker pull percona/percona-xtradb-cluster`
  + `docker load < /home/soft/pxc.tar.gz`

+ 更名：
  + docker tag percona/percona-xtradb-cluster pxc

+ 删除：
  + `docker rmi percona/percona-xtradb-cluster pxc`

+ 创建卷
  + `docker volume create --name v#`

+ 创建PXC集群mysql
  + `docker run -d -p 3306:3306 -v v2:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=abc123456 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=abc123456 -e CLUSTER_JOIN=node1 --privileged --name=node2 --net=net1 --ip 172.19.0.3 pxc`

### 创建内部网络

> docker虚拟机自带的网段为`172.17.0.*`，则`docker network create net1`的ip地址为`172.18.0.*`, 以此类推...
+ 创建内部网络：`docker network create net1`
+ 查看内部网络：`docker network inspect net1`
+ 删除内部网络：`docker network rm net1`
+ 由于创建内部网络没有指定 --subnet, run容器时出错：
  + docker: Error response from daemon: User specified IP address is supported only when connecting to networks with user configured subnets.
  + 解决方案 http://www.suohi.cc/posts/57be697d731d7c006f9daf74
  
  
