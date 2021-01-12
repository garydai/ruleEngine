# ruleEngine

可视化配置规则引擎

### ui

![image-20191127110020364](https://github.com/garydai/ruleEngine/blob/master/image-20191127110020364.png)

![image-20191127110120558](https://github.com/garydai/ruleEngine/blob/master/image-20191127110120558.png)

![image-20191127110138313](https://github.com/garydai/ruleEngine/blob/master/image-20191127110138313.png)

#### 框架

##### 后端

采用spring cloud微服务架构

  ![image-20191127111140350](https://github.com/garydai/ruleEngine/blob/master/image-20191127111140350.png)

##### 前端

vue

http://127.0.0.1:7777

```
npm install
npm run dev
```

##### skywalking

apache-skywalking-apm-bin/bin/startup.sh

http://127.0.0.1:8080

![image-20191127141520708](https://github.com/garydai/ruleEngine/blob/master/image-20191127141520708.png)

### 规则引擎

采用drool规则引擎

#### drool

将变量值放入hashmap，当作drool的输入fact

#### 自定义dsl语法

```
[when][]lt = <
[when][]le = <=
[when][]ge = >=
[when][]gt = >
[when][]eq = ==
[when][]ne = !=
[when][]and = &&
[when][]or = ||
[when][]contains = contains
[when][]notcontains = not contains
[when]input {field:[\w\.]+} = {field}()
[when]- {field:[\w\.]+} {operator} {value:.+} = {field} {operator} {value}
[when]resultInit = $ruleResult:RuleResult()
[then]result=$ruleResult.addRuleHit(drools.getRule().getName())
```

输入dsrl（业务人员编写的规则）

```
rule "test" 
when 
resultInit 
input 
- this["sex"] >= 1 
then result ;
end
rule "test2" 
when 
resultInit 
input 
- this["sex"] > 2  and  this["age"] < 12 
then result ;
end


```

输出drl

```
package xyz.sally.core.rules.uuiddcfdc76b4ed24e0a9bd79a86424fc903

import xyz.sally.core.fact.*
import xyz.sally.core.po.*
import java.util.HashMap;
import java.util.Map;rule "test" 
when 
$ruleResult:RuleResult() 
$map:HashMap(this["sex"]  >=  1) 
then $ruleResult.hitRule(drools.getRule().getName()) ;
end
rule "test2" 
when 
$ruleResult:RuleResult() 
$map:HashMap(this["sex"] > 2  &&  this["age"]  <  12) 
then 
System.out.println($map);
$ruleResult.hitRule(drools.getRule().getName()) ;
end


```





