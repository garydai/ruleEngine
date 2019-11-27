# ruleEngine

可视化配置规则引擎

### ui

![image-20191127110020364](https://github.com/garydai/ruleEngine/blob/master/image-20191127110020364.png)

![image-20191127110120558](https://github.com/garydai/ruleEngine/blob/raw/master/image-20191127110120558.png)

![image-20191127110138313](https://github.com/garydai/ruleEngine/blob/raw/master/image-20191127110138313.png)

#### 框架

##### 后端

采用spring cloud微服务架构

  ![image-20191127111140350](https://github.com/garydai/ruleEngine/blob/master/image-20191127111140350.png)

##### 前端

vue

### 规则引擎

采用drool规则引擎

#### drool

根据自定义变量，动态生成drool的输入fact

#### 自定义dsl

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

输入dsrl

```
rule "test" 
when 
resultInit 
input 
- sex >= 1 
then result ;
end
rule "test2" 
when 
resultInit 
input 
- sex > 2  and  age < 12 
then result ;
end

```

输出drl

```
package xyz.sally.core.rules.uuid67974f6b3897482aa7776a4bdd643b05

import xyz.sally.core.fact.*
import xyz.sally.core.po.*
rule "test" 
when 
$ruleResult:RuleResult() 
$input:xyz.sally.core.fact.input.Input67974f6b3897482aa7776a4bdd643b05(sex  >=  1) 
then $ruleResult.hitRule(drools.getRule().getName()) ;
end
rule "test2" 
when 
$ruleResult:RuleResult() 
$input:xyz.sally.core.fact.input.Input67974f6b3897482aa7776a4bdd643b05(sex  >  2  &&  age < 12) 
then $ruleResult.hitRule(drools.getRule().getName()) ;
end

```





