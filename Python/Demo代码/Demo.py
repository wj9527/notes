# -*- coding: UTF-8 -*-
while True:
    num = int(input("请输入数字  \n"))
    if num <= 0:
        print ("数据不能小于等于0")
    else:
        for x in range(1,num + 1):
            for y in range(1,x + 1):
                print ('%s x %s = %s' %(x, y, x*y))
