--------------------------------
kafka-topics.bat				|
--------------------------------
	kafka-topics.bat --zookeeper localhost:2181 --create --topic test1 --partitions 3 --relication-factor 1
	kafka-topics.bat --zookeeper localhost:2181 --describe --topic test1