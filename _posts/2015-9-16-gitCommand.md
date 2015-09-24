---
layout: default

title: git command

---

##常用git命令

###回滚

git reset --hard  xxx

###冲突

代码库中的文件完全覆盖本地工作版本. 

git reset --hard

git pull

如果希望保留生产服务器上所做的改动,仅仅并入新配置项, 处理方法如下:

git stash

git pull

git stash pop

然后可以使用git diff -w +文件名 来确认代码自动合并的情况.



