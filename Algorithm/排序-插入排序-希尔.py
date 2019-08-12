# 希尔排序
	* 它是插入排序经过改进后一个船新版本, 也叫做: 缩小增量排序
	* 基本思想: 
		1. 把记录按照下标的一定增量分组
			gap = length / 2
		2. 对每一组使用直接插入排序算法排序
		3. 随着增量的逐渐减少, 每组包含的关键词越来越多
			gap = gap / 2
		4. 当增量减至1的时候, 整个文件恰好被分成1组, 算法终止
			if gap = 1 : break
	

# 代码实现
def shellSort(arr):
    gab = len(arr) // 2

    while gab > 0:
        for i in range(gab, len(arr)):
            x = i - gab
            while x >= 0:
                if arr[x] > arr[x + gab]:
                    temp = arr[x]
                    arr[x] = arr[x + gab]
                    arr[x + gab] = temp
                x -= gab

        gab = gab // 2


arr = [5, 4, 7, 1, 8, 2, 9, 3, 6, 0]


shellSort(arr)

print(arr)


