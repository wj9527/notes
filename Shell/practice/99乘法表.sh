
#!/bin/bash
val=10
for (( i = 1 ;i < ${val} ; i++)); do
	for (( y = 1; y <= ${i}; y ++ )); do
		sum=$[${i} * ${y}]
		echo -n "${i} x ${y} = ${sum}	"
	done
	echo ""
done
