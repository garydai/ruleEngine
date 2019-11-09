# ruleEngine

#### 框架

#### drool

根据自定义变量，动态生成drool的输入fact



```
package com.ecreditpal.rules.profile

import java.util.HashMap
import java.util.List
import java.util.ArrayList

import com.ecreditpal.tshirt.rules.input.*
import com.ecreditpal.tshirt.rules.results.*
import com.ecreditpal.tshirt.rules.sdk.*
import com.ecreditpal.tshirt.rules.results.*
rule "联系方式中选取的相应APP是否在手机上(whatsapp、facebook、twitter、instagram)" 
when 
$ruleResult:RuleResult() 
 com.ecreditpal.tshirt.rules.input.ShawshankInputInfo(facebookOnPhone  ==  false  ||  whatsappOnPhone == false  ||  twitterOnPhone == false  ||  instagramOnPhone == false) 
then $ruleResult.addRuleHit(drools.getRule().getName()) ;
end
rule "手机APP中命中贷款APP列表的占比>=20% 或者个数 >= 5个" 
when 
$ruleResult:RuleResult() 
 com.ecreditpal.tshirt.rules.input.ShawshankInputInfo(loanAppCount  >=  5) 
then $ruleResult.addRuleHit(drools.getRule().getName()) ;
end
rule "凌晨一点到五点通话次数占比>5%" 
when 
$ruleResult:RuleResult() 
 com.ecreditpal.tshirt.rules.input.ShawshankInputInfo(callTime1to5Pct  >  0.05) 
then $ruleResult.addRuleHit(drools.getRule().getName()) ;
end
rule "家庭电话不在手机通话记录中" 
when 
$ruleResult:RuleResult() 
 com.ecreditpal.tshirt.rules.input.ShawshankInputInfo(callLogPhones  contains  homePhone) 
then $ruleResult.addRuleHit(drools.getRule().getName()) ;
end
rule "亲属联系人电话不在手机通话记录中" 
when 
$ruleResult:RuleResult() 
 com.ecreditpal.tshirt.rules.input.ShawshankInputInfo(callLogPhones  contains  relativePhone) 
then $ruleResult.addRuleHit(drools.getRule().getName()) ;
end
rule "家庭电话不在手机contact中" 
when 
$ruleResult:RuleResult() 
 com.ecreditpal.tshirt.rules.input.ShawshankInputInfo(contactPhones  contains  homePhone) 
then $ruleResult.addRuleHit(drools.getRule().getName()) ;
end
rule "手机GPS定位，家庭城市，工作城市不一致" 
when 
$ruleResult:RuleResult() 
 com.ecreditpal.tshirt.rules.input.ShawshankInputInfo(contactPhones  contains  relativePhone) 
then $ruleResult.addRuleHit(drools.getRule().getName()) ;
end
rule "家庭住址与单位地址不在同一城市" 
when 
$ruleResult:RuleResult() 
 com.ecreditpal.tshirt.rules.input.ShawshankInputInfo(homeCity  !=  workCity) 
then $ruleResult.addRuleHit(drools.getRule().getName()) ;
end
rule "包含短信类优质关键字短信条数>0" 
when 
$ruleResult:RuleResult() 
 com.ecreditpal.tshirt.rules.input.ShawshankInputInfo(positiveSmsCount  >  0) 
then $ruleResult.addRuleHit(drools.getRule().getName()) ;
end

```





