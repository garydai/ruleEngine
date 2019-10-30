---
layout: default

title: git command

---

## 常用git命令


![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/git.jpeg)  

### 流程

工作区 add 到 stage区，再commit到分支

### 本地代码库回滚

	git reset --hard  xxx

### 放弃工作区文件修改 

让文件回到最近一次git commit或git add时的状态，checkout出本地版本库里的文件

	git checkout -- file

### 放弃暂存区文件修改

把暂存区（stage）的文件修改撤销

	git reset HEAD file

### 关联远程库

	get remote add origin git@github.com:xxx/xxx.git

### 本地库推送到远程库

	git push -u origin master

### 从远程库clone

	git clone git@github.com:xxx/xxx.git

### 分支管理 
我们创建dev分支，然后切换到dev分支

	git checkout -b dev

git checkout命令加上-b参数表示创建并切换，相当于以下两条命令：

	git branch dev
	git checkout dev

合并某分支到当前分支

	git merge <name>

删除分支

	git branch -d <name>

### 冲突

代码库中的文件完全覆盖本地工作版本. 

	git reset --hard

	git pull

如果希望保留生产服务器上所做的改动,仅仅并入新配置项, 处理方法如下:

	git stash

	git pull

	git stash pop

然后可以使用git diff -w +文件名 来确认代码自动合并的情况.

### 标签

命令git tag <name>用于新建一个标签，默认为HEAD，也可以指定一个commit id；

git tag -a <tagname> -m "blablabla..."可以指定标签信息；

git tag -s <tagname> -m "blablabla..."可以用PGP签名标签；

命令git tag可以查看所有标签。

	
命令git push origin <tagname>可以推送一个本地标签；

命令git push origin --tags可以推送全部未推送过的本地标签；

命令git tag -d <tagname>可以删除一个本地标签；

命令git push origin :refs/tags/<tagname>可以删除一个远程标签。



	命令	作用域	常用情景
	git reset	提交层面	在私有分支上舍弃一些没有提交的更改
	
	git reset	文件层面	将文件从缓存区中移除
	
	git checkout	提交层面	切换分支或查看旧版本
	
	git checkout	文件层面	舍弃工作目录中的更改
	
	git revert	提交层面	在公共分支上回滚更改
	
	git revert	文件层面	（然而并没有）

	git push origin test:master         // 提交本地test分支作为远程的master分支 //好像只写这一句，远程的github就会自动创建一个test分支