
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
	print p

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

if __name__ == '__main__':
	print kmp('abcabcabe', 'abcabe')
