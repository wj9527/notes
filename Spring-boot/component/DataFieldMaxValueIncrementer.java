--------------------------------
DataFieldMaxValueIncrementer	|
--------------------------------
	# ���ڻ�ȡDB����������
	# ��ͬ��DB,�в�ͬ��ʵ�֣���Ҫ��2������
		AbstractSequenceMaxAbstractSequence
			* ʹ�ñ�׼�����ݿ����в�������ֵ��

		AbstractColumnMaxValueIncrementer
			* ʹ��һ��ģ�����еı��������ֵ
			* ����ͨ��cacheSize����ָ��������������������ڴ�������ֵ����󣬵�������һ���Ի�ȡcacheSize���������������Լ������ݿ���ʵĴ��������Ӧ�õ����ܡ�

			* mysqlʵ��
				MySQLMaxValueIncrementer

		
	
	# DataFieldMaxValueIncrementer �ӿڶ�����3����ȡ��һ������ֵ�ķ���
	����int nextIntValue()
			* ��ȡ��һ������ֵ,������������Ϊint

	����long nextLongValue()
			* ��ȡ��һ������ֵ��������������Ϊlong

	����String nextStringValue()
			* ��ȡ��һ������ֵ,������������ΪString
	

	# SpringBoot ����
		import javax.sql.DataSource;

		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Configuration;
		import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
		import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;

		@Configuration
		public class MySQLDataFieldMaxValueIncrementer {
			
			@Bean
			public DataFieldMaxValueIncrementer dataFieldMaxValueIncrementer (@Autowired DataSource dataSource) {
				MySQLMaxValueIncrementer mySQLMaxValueIncrementer  = new MySQLMaxValueIncrementer();
				/**
				 * ����Դ
				 */
				mySQLMaxValueIncrementer.setDataSource(dataSource);
				/**
				 * ά�������ı���
				 */
				mySQLMaxValueIncrementer.setIncrementerName("inrement");
				/**
				 * ά���������е�����
				 */
				mySQLMaxValueIncrementer.setColumnName("serial");
				/**
				 * �ַ����������䳤�ȣ����㳤�Ȼ���ǰ�����0
				 */
				mySQLMaxValueIncrementer.setPaddingLength(10);
				/**
				 * �Ƿ�ÿ�β�������ʹ���µ����ӣ�Ĭ��Ϊtrue
				 */
				mySQLMaxValueIncrementer.setUseNewConnection(false);
				/**
				 * ���û��������ĸ��������ڴ�������ֵ����󣬵�������һ���Ի�ȡcacheSize������
				 */
				mySQLMaxValueIncrementer.setCacheSize(1);
				
				return mySQLMaxValueIncrementer;
			}
		}
	
	# ʹ��
		@Autowired
		private DataFieldMaxValueIncrementer dataFieldMaxValueIncrementer;

		@Test
		public void test () {
			int val = this.dataFieldMaxValueIncrementer.nextIntValue();
			System.out.println(val);
		}