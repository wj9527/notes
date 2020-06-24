-------------------
冒泡排序
-------------------
	#!/bin/bash
	arr=(4 7 5 2 3 6 8 1 0 9)
	length=${#arr[*]}
	for (( i = 0; i < ${length}; i++ )); do
	  for (( y = ${i} + 1; y < ${length}; y++ )); do
		if (( ${arr[i]} > ${arr[y]} )); then
			temp=${arr[i]}
			arr[${i}]=${arr[y]}
			arr[${y}]=${temp}
		fi
	  done
	done
	echo ${arr[*]}

-------------------
遍历读取文本文件
-------------------

	file=/root/app.log
	while read line; do
		echo "${line}"
	done < ${file}



	#!/bin/bash
	file=/root/springboot.py
	line_number=1
	cat ${file} | while read line; do
		echo "$((line_number++)) ${line}"
	done