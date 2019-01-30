---
layout: default

title: docker-java

---

## docker-java

### 安装

```
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
sudo yum makecache fast
安装docker 
sudo yum -y install docker-ce
安装pip
sudo yum install python-pip -y
安装docker-compose
sudo pip install docker-compose
启动
sudo systemctl start docker

```

### 配置
dockerfile
```
FROM java:8

ENV TZ Asia/Shanghai

COPY pailie_wallet-*-SNAPSHOT.jar /opt/pailie_wallet_api/lib/pailie_wallet.jar
COPY conf /opt/pailie_wallet_api/conf

WORKDIR /opt/pailie_wallet_api/lib

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Dlogging.config=/opt/pailie_wallet_api/conf/logback.xml \
-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -Xms1024m -Xmx1024m", "-jar", "pailie_wallet.jar"]
```

docker-compose.yml
```
version: '3'
services:
  app-api:
    image: pailie_wallet:IMAGE_TAG
    volumes:
      - ~/Documents/pailie/pailie_wallet/log:/var/log/pailie_wallet_api
    ports:
      - SPRING_PORT:8091
    environment:
      - SPRING_PROFILES_ACTIVE=docker-staging
```

### 问题
docker images -a会显示所有repostory和tag都为空的中间镜像
