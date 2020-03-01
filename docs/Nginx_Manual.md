Tengine 安装步骤：
===
* 第一步：从http://tengine.taobao.org/download.html上下载相应的版本(或者wget http://tengine.taobao.org/download/tengine-2.3.0.tar.gz直接在Linux上用命令下载)

* 第二步：将下载好的文件上传至/opt/ 目录中并解压，解压： tar -zxvf tengine-2.1.0.tar.gz

* 第三步：安装tengine的依赖，依赖：gcc , openssl-devel ,  pcre-devel , zlib-devel （若已安装，可跳过此步）
yum -y install gcc openssl-devel pcre-devel zlib-devel

* 第四步：设置一下配置信息
注意：记得创建 /var/tmp/nginx/client/目录，否则后面启动会报错
```
./configure \
  --prefix=/usr/local/nginx/ \
  --error-log-path=/var/log/nginx/error.log \
  --http-log-path=/var/log/nginx/access.log \
  --pid-path=/var/run/nginx/nginx.pid  \
  --conf-path=/usr/local/nginx/nginx.conf \
  --sbin-path=/usr/local/sbin/tengine \
  --lock-path=/var/lock/nginx.lock \
  --with-http_ssl_module \
  --with-http_flv_module \
  --with-http_stub_status_module \
  --with-http_gzip_static_module \
  --http-client-body-temp-path=/var/tmp/nginx/client/ \
  --http-proxy-temp-path=/var/tmp/nginx/proxy/ \
  --http-fastcgi-temp-path=/var/tmp/nginx/fcgi/ \
  --http-uwsgi-temp-path=/var/tmp/nginx/uwsgi \
  --http-scgi-temp-path=/var/tmp/nginx/scgi \
  --with-pcre \
  --with-stream \
  --with-stream_ssl_module \
  --with-http_slice_module \
  --with-mail \
  --with-mail_ssl_module \
  --with-file-aio \
  --with-http_v2_module \
  --with-debug
  ```
详细配置及含义参考官网：http://nginx.org/en/docs/configure.html

* 第五步：安装
make && make install

* 第六步：创建nginx文件，并加入到启动服务中
```
# cd /etc/init.d/
# touch nginx
```
```
#!/bin/bash
#
# chkconfig: - 85 15
# description: nginx is a World Wide Web server. It is used to serve
# Source function library.
. /etc/rc.d/init.d/functions

# Source networking configuration.
. /etc/sysconfig/network

# Check that networking is up.
[ "$NETWORKING" = "no" ] && exit 0

nginx="/usr/local/nginx/tengine-2.1.0/sbin/nginx"
prog=$(basename $nginx)

NGINX_CONF_FILE="/usr/local/nginx/tengine-2.1.0/conf/nginx.conf"

#[ -f /etc/sysconfig/nginx ] && . /etc/sysconfig/nginx

lockfile=/var/lock/subsys/nginx

#make_dirs() {
#   # make required directories
#   user=`nginx -V 2>&1 | grep "configure arguments:" | sed 's/[^*]*--user=\([^ ]*\).*/\1/g' -`
#   options=`$nginx -V 2>&1 | grep 'configure arguments:'`
#   for opt in $options; do
#       if [ `echo $opt | grep '.*-temp-path'` ]; then
#           value=`echo $opt | cut -d "=" -f 2`
#           if [ ! -d "$value" ]; then
#               # echo "creating" $value
#               mkdir -p $value && chown -R $user $value
#           fi
#       fi
#   done
#}

start() {
    [ -x $nginx ] || exit 5
    [ -f $NGINX_CONF_FILE ] || exit 6
#    make_dirs
    echo -n $"Starting $prog: "
    daemon $nginx -c $NGINX_CONF_FILE
    retval=$?
    echo
    [ $retval -eq 0 ] && touch $lockfile
    return $retval
}

stop() {
    echo -n $"Stopping $prog: "
    killproc $prog -QUIT
    retval=$?
    echo
    [ $retval -eq 0 ] && rm -f $lockfile
    return $retval
}

restart() {
    configtest || return $?
    stop
    sleep 1
    start
}

reload() {
    configtest || return $?
    echo -n $"Reloading $prog: "
#  -HUP是nginx平滑重启参数
    killproc $nginx -HUP
    RETVAL=$?
    echo
}

force_reload() {
    restart
}

configtest() {
  $nginx -t -c $NGINX_CONF_FILE
}

rh_status() {
    status $prog
}

rh_status_q() {
    rh_status >/dev/null 2>&1
}

case "$1" in
    start)
        rh_status_q && exit 0
        $1
        ;;
    stop)
        rh_status_q || exit 0
        $1
        ;;
    restart|configtest)
        $1
        ;;
    reload)
        rh_status_q || exit 7
        $1
        ;;
    force-reload)
        force_reload
        ;;
    status)
        rh_status
        ;;
    condrestart|try-restart)
        rh_status_q || exit 0
            ;;
    *)
        echo $"Usage: $0 {start|stop|status|restart|condrestart|try-restart|reload|force-reload|configtest}"
        exit 2
esac
```
* 第七步：为/usr/local/sbin/nginx文件添加执行权限
`chown root. nginx` 将文件所属改成root，非root用户无法开1024以下的端口包括80/443
`chmod 755 nginx`  
`chmod u+s nginx` 给文件加root权限

* 第八步：启动并查看
service nginx start
---------------------
* 原文：https://blog.csdn.net/ArthurDev/article/details/73385351

Nginx 配置文件,参考`the complete nginx cookbook 2019`
===
* 配置四层协议代理stream模块,支持TCP & UDP
```
stream {
    #支持tcp [ssh mysql email  postgres] |  dns[udp protocol]
    upstream mysql_read {
        server read1.example.com:3306 weight=5;
         server read2.example.com:3306;
         server 10.10.12.34:3306 backup;
        }
        server {
        listen 3306;
        proxy_pass mysql_read;
        }
}
```
* 配置代理http模块
* 注意存在文件上传的接口需要配置 `client_max_body_size 100m;` 默认是1m;
* 配置tcp转发
 ```shell script
stream {
    log_format stream-basic '$remote_addr [$time_local] '
                            '$protocol $status $bytes_sent $bytes_received '
                            'upstream->$upstream_addr $session_time';
    access_log /var/log/nginx/stream-access.log stream-basic;

    upstream backends {
        server 172.17.0.2:3306;
        server 172.17.0.3:3306;
        server 172.17.0.4:3306;
    }

    server {
        listen 3306;
        proxy_pass backends;
    }
}
```
* 正向代理
```shell script
正向代理发生在 client 端，用户能感知到的，并且是用户主动发起的代理。

比如：翻墙。
我们不能访问外网，但是可以访问代理服务器，然后代理服务器帮我们从外网中获取数据。但是在使用之前，用户往往需要主动在client端配置代理。

黑客为了隐藏身份，用的就是正向代理。

|客户端+代理服务器|-->|目标服务器|
```
* 反向代理
```shell script
反向代理发生在 server端，从用户角度看是不知道发生了代理的（这个只有服务器工程师才知道）。

比如：
用户访问 服务器A，服务器A就给用户返回了数据。
但是服务器A上其实并没有数据，它是偷偷从服务器B上获取数据，然后再返回给用户的。
这个过程是在 server 端发生的，用户并不知道（只有服务器运维人员才知道）。

|客户端|-->|代理服务器+目标服务器|

```
* 负载均衡
```shell script
负载均衡是反向代理的一种运用。
客户端访问服务器，服务器会把请求分发给其它多个不同的服务器（即反向代理），从而减轻了单个服务器处理海量请求的压力，不会出现崩溃。

做了反向代理才能实现负载均衡。负载均衡是做反向代理的目的之一。
反向代理，是有把请求转发的能力，这个是基础
负载均衡，是把请求转发到不同的服务器上，均衡各个服务器
```