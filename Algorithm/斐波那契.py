
# 斐波那契数列,从第3项开始,每一项都等于前两项之和
# 1,1,2,3,5,8,13,21,34,55.....
# 可以使用递归

def fib(num):
	# 如果是前2项
    if num <= 2:
        return 1
    else:
        return fib(num - 2) + fib(num - 1)


for i in range(1,11):
    print(fib(i),end=',')

