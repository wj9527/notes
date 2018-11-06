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