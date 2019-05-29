# collection 必须为升序排序的有序数组,不存在返回负数
def indexedBinarySearch(collection, value):
    low = 0
    high = len(collection) - 1
    while(low <= high):
        mid = (low + high) // 2
        item = collection[mid]
        if item > value:
            high = mid - 1
        elif item < value:
            low = mid + 1
        else:
            return mid
    return -(low + 1)



# 获取中位数的下标,如果 start 和 end 过大的话,  在某些语言中可能会导致溢出, 需要换一种计算方式
	mid = low + (high - low) // 2
	

# 如果支持无符号位移运算的话, 更简单
	 mid = (low + high) >>> 1;