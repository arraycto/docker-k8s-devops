# Rancher+微服务

## 参考教程

+ [官方教程](https://www.cnrancher.com/docs/rancher/v1.x/cn/installing/installing-server/#single-container)
+ [慕课网教程](https://coding.imooc.com/lesson/187.html#mid=12896)
+ [51CTO教程](https://edu.51cto.com/center/course/lesson/index?id=323724)

## 安装

+ 内嵌数据库：`sudo docker run -d --restart=unless-stopped -p 8080:8080 rancher/server`
+ 外接数据库：`docker run -d --restart=unless-stopped -p 8080:8080 rancher/server --db-host myhost.example.com --db-port 3306 --db-user username --db-pass password --db-name cattle`

## 常见问题和注意事项

### 1.外接数据库时的命令必须根据上面教程里的适配自己的用户名和密码，要不连不上数据库地

![Rancher外接数据库注意事项](images/Rancher外接数据库注意事项.png)

### 2.想让启动的微服务实例能在内网内访问，网络必须选择"主机"而不是默认的"托管"

