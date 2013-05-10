---
layout: default
title: 中文分词

---

##中文分词
###what
###how
####实现第一版  
一个句子S由很多不同的分词组合,W01W02W03...W0n,W11W12W13...W1n,Wi1Wi2Win,
对于不同的分词结果,取P(Wi1)P(Wi2)P(Wi3)...P(Win)最大为最后的分词结果,比向前向后匹配概率要高    
(P(Wi1)等于Wi1在词库里的词频)
####实现第二版
采用HMM
S由S1S2S3...Sn,n个字组成,S1隐含的状态是S1是一个词语的开头或中间还是结尾.根据已知求隐含  
所以P(H1H2H3...Hn|S1S2S3...Sn)=**P(S1S2S3...Sn|H1H2H3...Hn)****P(H1H2H3...Hn)**/P(S1S2S3...Sn)  


**P(S1S2S3...Sn|H1H2H3...Hn)**=P(S1|H1)P(S2|H2)...P(Sn|Hn)  
P(S1|H1)=P(H1S1)/P(H1)  
含义:以H1H2..Hn分法,成为该句子的概率(开头结尾开头中间结尾...该分法组成句子的概率(不等于句子合不合法))

**P(H1H2H3...Hn)**=P(H1)P(H2|H1)P(H3|H1H2)...P(Hn|H1H2H3...)=**P(H1)P(H2|H1)P(H3|H2)...(Hn|Hn-1)**(2-gram)  
含义:马尔科夫链，Hn只有Hn-1决定。H1H2H3...Hn分法的概率   
#####结论公式
**P(H1H2H3...Hn|S1S2S3...Sn)~P(S1|H1)P(H1)P(S2|H2)P(H2|H1)...P(Sn|Hn)(Hn|Hn-1)**  
一个字有四种隐藏状态，s一个词的开头，e一个词的结尾，m一个词的中间，a单个字。 
状态数目
states:{s,e,m,a}  
s有一定的概率是字X  
每个状态下可能的观察值
observations:{X}  
初始状态空间的概率分布  

状态转移  
transition_probility:{
s:{e:x, m:x, a:x}  
e:{}...
}
####第三版数学之美

