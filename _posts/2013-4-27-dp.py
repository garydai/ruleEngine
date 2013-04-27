#coding: utf-8


def dp():
	a = ' AGTCTGACGC'
	b = ' AGTAAGTAGGC'
	DP = [[100 for col in range(len(b))] for row in range(len(a))]
	DP[0][0] = 0
	#print DP
	for i in range(len(a)):
		DP[i][0] = i
	for i in range(len(b)):
		DP[0][i] = i
	#print DP
	for i in range(1, len(a)):
		for j in range(1, len(b)):
			if a[i] == b[j]:
				DP[i][j] = min(DP[i][j], DP[i - 1][j - 1])#相等

			DP[i][j] = min(DP[i - 1][j - 1] + 1, DP[i][j])#替换
			DP[i][j] = min(DP[i - 1][j] + 1, DP[i][j])#插入a[i]or删除a[i]
			DP[i][j] = min(DP[i][j - 1] + 1, DP[i][j]) #插入b[j]or删除b[j]
			#print i, j, DP[i][j]

	#print DP

	print DP[len(a) - 1][len(b) - 1]


if __name__ == '__main__':
	dp()