---
layout: default

title: filefeat+logstash+es+kibana

---

## filefeat+logstash+es+kibana

### filefeat
```
wget https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-6.6.0-linux-x86_64.tar.gz

./filebeat -e -c filebeat.yml -d "Publish"
```
### es
```
docker pull docker.elastic.co/elasticsearch/elasticsearch:5.6.9

sudo docker run -d -p 9200:9200 -p 9300:9300 -e "xpack.security.enabled=false" -e ES_JAVA_OPTS="-Xms512m -Xmx512m" -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:5.6.9

或者

wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-6.6.0.tar.gz
bin/elasticsearch
```
### logstash
```
wget https://artifacts.elastic.co/downloads/logstash/logstash-6.6.0.tar.gz
bin/logstash -f logstash.conf

logstash.con
input {
  beats {
    port => 5044
  }
}

filter {
     grok {
        # 筛选过滤
        match => {
          "message" => "(?<date>\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}:\d{2}.\d{3}) \[(?<thread>[A-Za-z0-9/- ]{4,90})\] (?<level>[A-Z]{4,5})\s+(?<class>[A-Za-z0-9/.]{4,50}) - (?<msg>.*)"
        }
        remove_field => ["message"]
     }
}
output {
  elasticsearch {
    hosts => ["http://127.0.0.1:9200"]
    index => "%{[@metadata][beat]}-2-%{[@metadata][version]}-%{+YYYY.MM.dd}"
    #user => "elastic"
    #password => "changeme"
  }
}

```
### kibana
```
wget https://artifacts.elastic.co/downloads/kibana/kibana-6.6.0-linux-x86_64.tar.gz
Set elasticsearch.url to point at your Elasticsearch instance
bin/kibana
```

