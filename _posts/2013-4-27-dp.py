#coding: utf-8


def dp(self):
	a = 'abcd'
	b = 'adce'
	for i in range(len(a)):
		for j in range(len(b)):
			if a[i] == b[j]:
				DP[i][j] = DP[i - 1][j - 1]
			else:
				DP[i][j] = min(DP[i - 1][j] + 1, DP[i][j])#插入a[i]or删除a[i]
				DP[i][j] = min(DP[i][j - 1] + 1, DP[i][j]) #插入b[j]or删除b[j]