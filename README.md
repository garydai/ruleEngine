# ruleEngine

#### 框架

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



