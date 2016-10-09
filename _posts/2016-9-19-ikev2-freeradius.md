---
layout: default

title: centos安装搭建ikev2+freeradius

---

##centos安装搭建ikev2+freeradius

###安装 strongSwan

	wget https://download.strongswan.org/strongswan-5.5.0.tar.bz2
需要下载源文件，enable radius，编译
	
		yum install gcc
		yum install gmp-devel
		
		centos7 yum instlal mysql-server 失败，需要加入
		sudo rpm -Uvh http://dev.mysql.com/get/mysql-community-release-el7-5.noarch.rpm
		yum install mysql
		
		./configure --prefix=/usr --sysconfdir=/etc/strongswan  --enable-xauth-eap --enable-openssl --enable-ext-auth --enable-nat-transport --enable-sql --enable-mysql  --enable-shared --enable-md4 --enable-eap-mschapv2 --enable-eap-aka --enable-eap-aka-3gpp2 --enable-eap-gtc --enable-eap-identity  --enable-eap-md5 --enable-eap-peap --enable-eap-radius --enable-eap-sim --enable-eap-sim-file --enable-eap-simaka-pseudonym --enable-eap-simaka-reauth --enable-eap-simaka-sql --enable-eap-tls --enable-eap-tnc --enable-eap-ttls
		make
		make install
	
###生成证书
同上篇博文
	strongswan命令改为ipsec
	
###配置 vpn
	vi /etc/strongswan/ipsec.conf
	
	rightauth = eap-radius

###修改 dns 配置
加入插件

	vi /etc/strongswan/strongswan.d/charon.conf
	
	plugins {
         eap-radius {
               accounting = yes
               servers {
                  radiusServer {
                       secret = testing123
                       address = 127.0.0.1
                       auth_port = 1812   # default
                       acct_port = 1813   # default
                   }
               }
          }
       }


###配置验证方式的用户名与密码
	
	
###开启内核转发


###配置防火墙

加入
	vi /etc/sysconfig/iptables
	
	-A INPUT  -p udp --dport 1812 -j ACCEPT
	-A INPUT  -p tcp --dport 1813 -j ACCEPT
	

	systemctl restart iptables
	

###配置完防火墙后重启 strongswan 服务

	

###客户端配置

	psk不需要下载证书

###配置freeradius

	yum install -y freeradius freeradius-mysql freeradius-utils 
	
	vim /etc/raddb/users
	查找steve Cleartext-Password，取消注释
	
	不要忘了上面的防火墙设置
	
	测试是否配置成功
	radiusd -X
	radtest steve testing localhost 0 testing123
	
	创建数据库
	create database radius;
	grant all on radius.* to radius@localhost identified by "radpass"
	
	导入表结构
	mysql -u root radius < /etc/raddb/mods-config/sql/main/mysql/schema.sql 
	
	建立表信息
	INSERT INTO radgroupreply (groupname,attribute,op,VALUE) VALUES ('user','Auth-Type',':=','Local');
	INSERT INTO radgroupreply (groupname,attribute,op,VALUE) VALUES ('user','Service-Type',':=','Framed-User');
	INSERT INTO radgroupreply (groupname,attribute,op,VALUE) VALUES ('user','Framed-IP-Address',':=','255.255.255.255');
	INSERT INTO radgroupreply (groupname,attribute,op,VALUE) VALUES ('user','Framed-IP-Netmask',':=','255.255.255.0');	
	
	修改认证配置
	cd /etc/raddb/mods-enabled
	ln -s ../mods-available/sql
	
	修改配置文件
	vi /etc/raddb/mods-available/sql
	找到driver="rlm_sql_null" 修改为 rlm_sql_mysql 
	dialect="sqlite"改为mysql
	
	
	radiusd start
	
[http://download.csdn.net/detail/cluniquecui/8336111](http://download.csdn.net/detail/cluniquecui/8336111)







    


