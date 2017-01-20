---
layout: default

title: mysql主从分离

---

##mysql主从分离

GRANT REPLICATION SLAVE ON *.* to 'rep1'@'45.76.100.149' identified by 'daitechang';

关闭主从

stop slave;

show slave status\G
	 
[http://www.cnblogs.com/xuejie/archive/2013/03/26/2983601.html](http://www.cnblogs.com/xuejie/archive/2013/03/26/2983601.html)






    


