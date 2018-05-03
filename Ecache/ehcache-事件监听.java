
-----------------------------
事件监听					 |
-----------------------------
	# 实现 CacheEventListener 接口
		import org.ehcache.event.CacheEvent;
		import org.ehcache.event.CacheEventListener;
		import org.ehcache.event.EventType;
		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;

		public class Li implements CacheEventListener<Long,String>{
			
			private static final Logger LOGGER = LoggerFactory.getLogger(Li.class);

			@Override
			public void onEvent(CacheEvent<? extends Long, ? extends String> event) {
				Long key = event.getKey();
				String newValue = event.getNewValue();
				String oldValue = event.getOldValue();
				EventType eventType = event.getType();
				LOGGER.info("key={},newValue={},oldValue={},eventType={}",key,newValue,oldValue,eventType);
			}
		}
	
