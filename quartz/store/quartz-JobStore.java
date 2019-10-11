----------------------------
JobStore					|
----------------------------
	# JobStore负责跟踪, 提供给调度程序的所有"工作数据": jobs, triggers, Calendar... 等

	# 子类
		AbstractTerracottaJobStore
			|-TerracottaJobStore
		JobStoreSupport
			|-JobStoreCMT
				|-LocalDataSourceJobStore
			|-JobStoreTX
		RAMJobStore
		ClusteredJobStore
			|-DefaultClusteredJobStore
		TerracottaJobStoreExtensions
			|-PlainTerracottaJobStore
	
	