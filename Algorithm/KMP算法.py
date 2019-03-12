
# KMP算法的核心,是一个被称为部分匹配表PMT(Partial Match Table)的数组

# 关于前缀和后缀(字符串本身并不是自己的前/后缀)
    Harry Potter
		Harry 前缀集合	{"H", "Ha", "Har", "Harr"}
		Potter 后缀集合	{"otter", "tter", "ter", "er", "r"}

# PMT中的值是字符串的前缀集合与后缀集合的交集中最长元素的长度
	"aba" 
		前缀 {"a", "ab"}		前缀集合
		后缀 {"ba" ,"a"}		后缀集合
		PMT =  {"a"} = 1		前后缀集合的交集中,最长的元素长度是1
	

	"ababa"
		前缀 {"a", "ab", "aba", "abab"}
		后缀 {"baba", "aba" ,"ba" ,"a"}
		PMT = {"a", "aba"} = 3

# ababababca 的PMT
	char	'a' 'b' 'a' 'b' 'a' 'b' 'c' 'a'
	index	 0	 1	 2	 3	 4	 5   6	 7
	value	 0   0   1   2	 3   4   0   1


# 这TM啥啊这？