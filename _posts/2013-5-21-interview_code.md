---
layout: default

title: 面试编程题 

---
## 面试编程题
### 题目
括号匹配：给定字符串，输出括号是否匹配，例如，"()" yes；")(" no；"(abcd(e)" no；"(a)(b)" yes。要求必须用递归写，整个实现不可以出现一个循环语句。
### 算法
遇到'(',count++，遇到')'，count--，递归过程中，如果count<0，则括号不匹配

### code
	#coding: utf-8
	def dfs(string, count):
		if count < 0:
			return False
		if len(string) == 1:
			if string == '(':
				return False
			elif string == ')':
				count = count - 1
	
			if count == 0:
				return True
			else:
				return False
		else:
	
			if string[0] == '(':
				count = count + 1
			elif string[0] == ')':
				count = count - 1
			string = string[1:len(string)]
			return dfs(string, count)
	
	if __name__ == '__main__':
		print dfs('(a)(b))11()(a)(b)1', 0)


