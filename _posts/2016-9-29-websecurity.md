---
layout: default

title: web安全

---

## web安全

### PHP安全函数
	
1.addslashes

对SQL语句中的特殊字符进行转义操作，包括(‘), (“), (), (NUL)四个字符

2.htmlspecialchars


htmlspecialchars把HTML中的几个特殊字符转义成HTML Entity(格式：&xxxx;)形式，包括(&),('),("),(<),(>)五个字符。

		& (AND) => &amp;
		” (双引号) => &quot; (当ENT_NOQUOTES没有设置的时候)
		‘ (单引号) => &#039; (当ENT_QUOTES设置)
		< (小于号) => &lt;
		> (大于号) => &gt;   
3.htmlentities

htmlentities把HTML中可以转义的内容转义成HTML Entity。html_entity_decode为htmlentities的decode函数。

4.mysql_real_escape_string

mysql_real_escape_string会调用MySQL的库函数mysql_real_escape_string，对(\x00), (\n), (\r), (), (‘), (\x1a)进行转义，即在前面添加反斜杠()，预防SQL注入。

5.strip_tags
 
strip_tags会过滤掉NUL，HTML和PHP的标签。


[http://weizhifeng.net/php-security-functions.html](http://weizhifeng.net/php-security-functions.html)



### SQL注入

http://www.techug.com/post/sql-injection-attacks-by-example.html

    


