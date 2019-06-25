# Nginx不同安装方式下的不同目录结构

+ 1.`yum install -y nginx`
+ 2.docker安装
+ 3.[官方安装包](http://nginx.org/en/download.html)安装

## 1&2.yum或者docker安装nginx

+ centos下：`yum install -y nginx`
+ docker:`docker run -d -p 80:80 nginx`

手动安装完毕，执行`nginx -V`可以看到详细的配置信息

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
--http-log-path=/var/log/nginx/access.log # http请求的访问状态 
```

## 3.官方安装包手动安装

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


总之上面的两种安装方式本质都是一样的，就是安装文件存放位置不同，自己要根据情况酌情判断，更多的可以参考如下自己的github

+ [Nginx快速入门](tttps://github.com/19920625lsg/docker-k8s-devops/blob/master/Nginx/Nginx快速入门/README.md#2nginx安装)
+ [spring-boot-online-exam/backend/README.md#7配置nginx](https://github.com/19920625lsg/spring-boot-online-exam/blob/master/backend/README.md#7配置nginx)
  