# easy-mock容器化部署

## 0.目录解析

```shell
easymock
├── data
│   ├── db # mongodb映射路径
│   └── redis # redis映射路径
├── docker-compose.yml # docker-compose up -d执行入口
├── logs # 网站日志
└── production.json # 网站的配置文件，官方仓库的default.json
```

## 1.将当前目录上传到linux上

> 记得要给logs赋予可执行权限`chmod 777 logs`，否则会报Permission Denied错误

## 2.安装docker-compose

> 参考博文： https://www.jianshu.com/p/6f9ce31b9aa7

+ [下载可执行文件docker-compose-Linux-x86_64](https://github.com/docker/compose/releases/download/1.24.1/docker-compose-Linux-x86_64)
+ 上传`docker-compose-Linux-x86_64`到 **/usr/local/bin/** 目录下并改名,然后赋予可执行权限

  ```shell
  cd  /usr/local/bin
  mv  docker-compose-Linux-x86_64  docker-compose
  sudo chmod +x docker-compose
  ```

+ 查看docker-compose版本号,显示如下表示成功
  
  ```shell
    ➜  easymock docker-compose -v
    docker-compose version 1.24.1, build 4667896b
  ```

## 3.在easymock目录执行`docker-compose up -d`，然后访问`http://ip:7300`即可开始使用easy-mockla

> `docker-compose down`可以停止并删除容器，如果实在内网环境下最好下手动下载好docker-compose.yml里面需要的镜像
