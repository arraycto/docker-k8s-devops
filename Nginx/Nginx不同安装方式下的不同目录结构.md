# Nginx不同安装方式下的不同目录结构

+ 1.`yum install -y nginx`
+ 2.docker安装
+ 3.[官方安装包](http://nginx.org/en/download.html)安装

## 1&2.yum和docker安装nginx

### `nginx -V`解析

+ centos下：`yum install -y nginx`
+ docker:`docker run -d -p 80:80 nginx`

手动安装完毕，执行`nginx -V`可以看到详细的配置信息(容器需要先用`docker exec -it 容器id /bin/bash`进去)

```shell
configure arguments: --prefix=/usr/share/nginx --sbin-path=/usr/sbin/nginx --modules-path=/usr/lib64/nginx/modules --conf-path=/etc/nginx/nginx.conf --error-log-path=/var/log/nginx/error.log --http-log-path=/var/log/nginx/access.log --http-client-body-temp-path=/var/lib/nginx/tmp/client_body --http-proxy-temp-path=/var/lib/nginx/tmp/proxy --http-fastcgi-temp-path=/var/lib/nginx/tmp/fastcgi --http-uwsgi-temp-path=/var/lib/nginx/tmp/uwsgi --http-scgi-temp-path=/var/lib/nginx/tmp/scgi --pid-path=/run/nginx.pid --lock-path=/run/lock/subsys/nginx --user=nginx --group=nginx --with-file-aio --with-ipv6 --with-http_auth_request_module --with-http_ssl_module --with-http_v2_module --with-http_realip_module --with-http_addition_module --with-http_xslt_module=dynamic --with-http_image_filter_module=dynamic --with-http_geoip_module=dynamic --with-http_sub_module --with-http_dav_module --with-http_flv_module --with-http_mp4_module --with-http_gunzip_module --with-http_gzip_static_module --with-http_random_index_module --with-http_secure_link_module --with-http_degradation_module --with-http_slice_module --with-http_stub_status_module --with-http_perl_module=dynamic --with-mail=dynamic --with-mail_ssl_module --with-pcre --with-pcre-jit --with-stream=dynamic --with-stream_ssl_module --with-google_perftools_module --with-debug --with-cc-opt='-O2 -g -pipe -Wall -Wp,-D_FORTIFY_SOURCE=2 -fexceptions -fstack-protector-strong --param=ssp-buffer-size=4 -grecord-gcc-switches -specs=/usr/lib/rpm/redhat/redhat-hardened-cc1 -m64 -mtune=generic' --with-ld-opt='-Wl,-z,relro -specs=/usr/lib/rpm/redhat/redhat-hardened-ld -Wl,-E'
```

核心是下面几部分

```shell
--prefix=/usr/share/nginx # 前端资源存放，里面的html文件夹一般是用来存放自定义网站的
--sbin-path=/usr/sbin/nginx # nginx可执行文件
--modules-path=/usr/lib64/nginx/modules # nginx的官方标准模块和和第三方模块的存放地址
--conf-path=/etc/nginx/nginx.conf # nginx的主配置文件地址
--error-log-path=/var/log/nginx/error.log # 服务的错误状态日志
--http-log-path=/var/log/nginx/access.log # http请求的访问状态日志
```

### 主配置文件`/etc/nginx/nginx.conf`默认内容，含解析

```nginx
# 运行root的用户(可以赋予最高权限，不会出现403 Forbidden的情况)
user  root;
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

### 虚拟主机配置文件`/etc/nginx/conf.d/default.conf`解析

```nginx
server {
    listen       80;
    server_name  localhost;

    #charset koi8-r;
    #access_log  /var/log/nginx/host.access.log  main;

    location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }

    #error_page  404              /404.html;

    # redirect server error pages to the static page /50x.html
    #
    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   /usr/share/nginx/html;
    }

    # proxy the PHP scripts to Apache listening on 127.0.0.1:80
    #
    #location ~ \.php$ {
    #    proxy_pass   http://127.0.0.1;
    #}

    # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
    #
    #location ~ \.php$ {
    #    root           html;
    #    fastcgi_pass   127.0.0.1:9000;
    #    fastcgi_index  index.php;
    #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
    #    include        fastcgi_params;
    #}

    # deny access to .htaccess files, if Apache's document root
    # concurs with nginx's one
    #
    #location ~ /\.ht {
    #    deny  all;
    #}
}
```

## 3.官方安装包手动安装

### 安装及解析

首先安装`gcc-c++`、`pcre`和`pcre-devel`、`zlib`和`zlib-devel`、`openssl`和`openssl-devel`

```shell
yum install gcc-c++
yum install pcre pcre-devel
yum install zlib zlib-devel
yum install openssl openssl-devel
```

去[nginx官网](http://nginx.org/en/download.html)下载最新的stable版本 然后解压并配置nginx

```shell
cd /root/l00379880/soft/
tar -zxvf nginx-1.12.0.tar.gz
cd nginx-1.12.0/ # 然后执行下面的命令即可
# 编译并安装nginx
./configure & make & make install
```

现在nginx安装完毕，所有的文件都在 **/usr/local/nginx** 下，目录层次如下

```shell
[root@heiheihei nginx]# pwd
/usr/local/nginx # 安装的根目录
[root@heiheihei nginx]# tree /usr/local/nginx
/usr/local/nginx
├── conf # 配置目录，相当于上面的/etc/nginx/
|   ├── conf.d # 自定义虚拟主机配置，需要自己创建，里面含有一个default.conf，下面有讲
│   ├── fastcgi.conf # fastcgi配置
│   ├── fastcgi.conf.default # fastcgi默认配置
│   ├── fastcgi_params # fastcgi参数
│   ├── fastcgi_params.default # fastcgi参数备份
│   ├── koi-utf # 编码转换映射转化文件
│   ├── koi-win # 编码转换映射转化文件
│   ├── mime.types # 设置http协议的Content-Type与扩展名对应关系
│   ├── mime.types.default # 上面文件的备份
│   ├── nginx.conf # 主配置文件
│   ├── nginx.conf.default # 主配置文件备份
│   ├── scgi_params # scgi参数
│   ├── scgi_params.default # scgi参数备份
│   ├── uwsgi_params # uwsgi参数
│   ├── uwsgi_params.default # uwsgi参数备份
│   └── win-utf # 编码转换映射转化文件
├── html # 项目的前端文件,相当于上面的/usr/share/nginx/html/
│   ├── 50x.html # 访问出错时重定向的错误页面
│   └── index.html # 访问的首页
├── logs # 日志文件,相当于上面的/var/log/nginx/
└── sbin # 可执行文件,相当于上面的/usr/sbin/
    └── nginx # nginx的可执行文件，所有nginx命令都在这执行

4 directories, 18 files
```

### 主配置文件`/usr/local/nginx/conf/nginx.conf`解析

```nginx
user  root;
worker_processes  1;

# error.log处理的是处理服务的错误状态
# error_log  logs/error.log;
# error_log  logs/error.log  notice;
# error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    # 错误日志文件的位置和输出级别
    # log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                   '$status $body_bytes_sent "$http_referer" '
    #                   '"$http_user_agent" "$http_x_forwarded_for"';

    # access_log记录的是http请求的访问状态，依赖于log_format配置。
    # access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    # 引入外部配置文件，包含虚拟主机的设置,一个配置文件就是一个虚拟主机。下面有讲，其实自定义的配置文件都可以放在这个目录下
    include /usr/local/nginx/conf/conf.d/*.conf;
}
```

**各个字段的解析如下：**

```nginx
# 1.全局配置
# 运行root的用户(可以赋予最高权限，不会出现403 Forbidden的情况)
user  root;
# 工作进程的数量，可以根据CPU的核心总数来设置
worker_processes  4;

# 错误日志文件的位置和输出级别
#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

# PID文件的位置
#pid        logs/nginx.pid;

# 2.工作模式配置
events {
    # 每个进程最大处理的连接数.总连接数 = worker_processes(CPU核心数) * worker_connections(单CPU进程的最大连接数)
    worker_connections  1024;
}

# 3.HTTP配置
http {
    # 支持的媒体类型.支持的全部媒体类型见/usr/local/nginx/conf/mime.types
    include       mime.types;
    # 默认的类型
    default_type  application/octet-stream;

    # 日志格式,$xxxx是nginx的系统变量
    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    # 访问日志文件的位置，http请求的访问状态，这个main必须和上面log_format里的对应相同
    # access_log  logs/access.log  main;

    # 是否调用sendfile函数来输出文件
    sendfile        on;
    # 是否在一个数据包中发送所有的文件，默认是on
    #tcp_nopush     on;

    # 连接超时时间,单位是s，秒
    keepalive_timeout  65;

    # 开启gzip压缩
    #gzip  on;

    # 引入外部配置文件，包含虚拟主机的设置,一个配置文件就是一个虚拟主机。下面有讲，其实自定义的配置文件都可以放在这个目录下
    include /usr/local/nginx/conf/conf.d/*.conf;
}
```

### 虚拟主机配置文件

> 上面的nginx.conf的最后一行配置了会自动引入 **/usr/local/nginx/conf/conf.d** 下面的所有conf配置文件，这个目录下默认有的 **/usr/local/nginx/conf/conf.d/default.conf** 是指**虚拟主机配置文件，可以定义多个虚拟主机配置文件**,默认的default.conf内容如下

```nginx
# 虚拟主机的配置
server {
    # 监听端口
    listen       80;
    # 服务器域名
    server_name  localhost;

    # 网页的默认编码
    #charset koi8-r;
    # 访问该虚拟主机的日志位置,http请求的访问状态
    #access_log  logs/host.access.log  main;

    # 根据目录配置，nginx对外的访问目录和首页入口
    location / {
        # 网站根目录的配置
        root   /usr/local/nginx/html;
        # 默认首页
        index  index.html index.htm;
    }

    # 404错误的反馈页面
    #error_page  404              /404.html;

    # 50x错误页面的配置
    # redirect server error pages to the static page /50x.html
    # 50x错误的反馈页面
    error_page   500 502 503 504  /50x.html;
    # 50x错误页面的路径
    location = /50x.html {
        root   /usr/local/nginx/html;
    }
}
```

## 总结

> 总之上面的两种安装方式本质都是一样的，就是安装文件存放位置不同，自己要根据情况酌情判断，更多的可以参考如下自己的github

+ [Nginx快速入门](https://github.com/19920625lsg/docker-k8s-devops/blob/master/Nginx/Nginx快速入门/README.md#2nginx安装)
+ [spring-boot-online-exam/backend/README.md#7配置nginx](https://github.com/19920625lsg/spring-boot-online-exam/blob/master/backend/README.md#7配置nginx)
  