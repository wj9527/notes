# 汉诺塔
	* 思路: 不管有多少个盘, 都看做俩个




# 代码实现
def hanoitower(number, a, b, c):
    if number == 1:
        print('第[%d]个盘从 [%s] -> [%s]' % (number, a, c))
    else:
        hanoitower(number - 1, a, c, b)
        print('第[%d]个盘从 [%s] -> [%s]' % (number, a, c))
        hanoitower(number - 1, b, a, c)


hanoitower(5, 'a', 'b', 'c')