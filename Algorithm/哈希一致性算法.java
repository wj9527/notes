
# Hash一致性算法
	* 用于客户端在N多服务端节点中选择一个进行连接
	* 普通的 hash(key) % nodes.size 会带来一些问题
		* 当节点新增,删除后,会有大量的客户端会被重新分配

# Java Demo

import java.util.SortedMap;
import java.util.TreeMap;


public class HashDemo {
	public static void main(String[] args) {
		System.out.println(consistentHashAlgorithmVirtual());
	}
	
	// 虚拟节点,解决环行圈儿中分布不均的问题
	public static String consistentHashAlgorithmVirtual() {
		// 每个节点的虚拟节点数量
		int virtualCount = 5;
		// 节点容器
		TreeMap<Integer,String> treeMap = new TreeMap<>();
		// 节点
		String[] servers = new String[] {
			"192.168.0.1:1024",
			"192.168.0.2:1024",
			"192.168.0.3:1024",
			"192.168.0.4:1024",
			"192.168.0.5:1024"
		};
		for(String server : servers) {
			// 为每个节点生成虚拟节点
			for(int x = 0 ;x < virtualCount ; x ++) {
				String finalName = server + "&&" + x;
				// 计算出每个节点的hash
				int hashCode = Math.abs(finalName.hashCode());
				treeMap.put(hashCode, finalName);
				System.out.println("hashCode=" + hashCode +   " server=" + finalName);
			}
		}

		// 客户端表标识以及hashCode
		String key = "client";
		int hashCode = Math.abs(key.hashCode());
		
		System.out.println("hashCode=" + hashCode);
		
		// 大于该hash的map
		SortedMap<Integer, String> tailMap = treeMap.tailMap(hashCode);
		if(!tailMap.isEmpty()) {
			// 有，则使用第一个(大于该hash的第一个)
			return tailMap.get(tailMap.firstKey());
		}else {
			//没则使用所有节点的第一个
			return treeMap.get(treeMap.firstKey());
		}
	}
	
	public static String consistentHashAlgorithm() {
		// 节点容器
		TreeMap<Integer,String> treeMap = new TreeMap<>();
		// 节点
		String[] servers = new String[] {
			"192.168.0.1:1024",
			"192.168.0.2:1024",
			"192.168.0.3:1024",
			"192.168.0.4:1024",
			"192.168.0.5:1024"
		};
		for(String server : servers) {
			int hashCode = Math.abs(server.hashCode());
			treeMap.put(hashCode, server);
			// 计算出每个节点的hash
			System.out.println("hashCode=" + hashCode +   " server=" + server);
		}

		// 客户端表标识以及hashCode
		String key = "client";
		int hashCode = Math.abs(key.hashCode());
		
		System.out.println("hashCode=" + hashCode);
		
		
		// 大于该hash的map
		SortedMap<Integer, String> tailMap = treeMap.tailMap(hashCode);
		if(!tailMap.isEmpty()) {
			// 有，则使用第一个(大于该hash的第一个)
			return tailMap.get(tailMap.firstKey());
		}else {
			//没则使用所有节点的第一个
			return treeMap.get(treeMap.firstKey());
		}
	}
}
