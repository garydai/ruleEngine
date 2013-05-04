#coding: utf-8
p = [-1]
def ini(pattern):
	for i in range(1, len(pattern)):
		if pattern[i] == pattern[0]:
			p.append(0)
		else:
			p.append(-1)

		j = i - 1
		while(j != -1):
			if pattern[p[j] + 1] == pattern[i]:
				p[i] = p[j] + 1
				break
			else:
				j = p[j]
	#print p

def kmp(s, t):
	ini(t)
	j = 0
	i = 0
	while(i != len(s)):
		while(j != len(t)):
			if t[j] == s[i]:
				j = j + 1
				i = i + 1 
			else:
				if j == 0:
					i = i + 1
					break
				j = p[j - 1] + 1 
		if j == len(t):
			return 1
	return 0

preGS = []
GS = []
BC = {}
#O(n^2/2)
def pre_build(pattern):
	#以i为右边界与后缀相同的字符个数
	for i in range(len(pattern)):
		GS.append(-1)
		t = i
		j = len(pattern) - 1
		while(j >= 0 and t >= 0 and t != j):
			if pattern[t] == pattern[j]:
				t = t - 1
				j = j - 1
			else:
				break
		preGS.append(i - t)
	#print 'preGS', preGS

def build_good_shuffix(pattern):
	pre_build(pattern)

	#情况三,模式串的前缀=后缀
	for i in range(len(pattern)):  

##		if preGS[i] == i + 1:
			#最左的后缀字符下标
##			GS[len(pattern) - preGS[i]] = len(pattern) - preGS[i]
	#情况二,模式串的子串=后缀
##	for i in range(len(pattern)):
    #情况二和三,同下代码
    #等式右边表示模式串移动多少距离
	    if preGS[i] != 0:
			GS[len(pattern) - preGS[i]] = len(pattern) - preGS[i]
	#print 'GS' ,GS


def build_bad_char(pattern):
	for i in range(len(pattern)):
			BC[pattern[i]] = i
	#print 'BC', BC

def bm(s, t):
	build_good_shuffix(t)
	build_bad_char(t)
	if len(s) < len(t):
		return False
	i = len(t) - 1
	j = len(t) - 1
	while(i < len(s)):
		while(j >= 0):
			if s[i] == t[j]:
				i = i - 1
				j = j - 1
			else:
				break
		if j == -1:
			return True
		else:
			skip = GS[j]
			if(BC.has_key(s[i])):
				if BC[s[i]] < j:
					skip = max(skip, j - BC[s[i]])
			if skip <= 0:
				skip = len(t)
		#print 'skip', skip

		i = i + skip
		j = len(t) - 1	
		#print 'i', i
		#print 'j', j
	return False
		

if __name__ == '__main__':
	print kmp('faeadsfasdfcasdfasdfasdfabcxsdfsadfadfxdesadffeabcxxxabcdwawasdfsdfsaasdf', 'abcxxxabc')
	print bm('faeadsfasdfasaewcaasdfasdfdbcxsdfsadfadfxdesadffeabcxxxabcdwawasdfsdfsaasdf', 'abcxxxabc')
