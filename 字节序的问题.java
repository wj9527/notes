--------------------------
�ֽ��������
--------------------------
	# �����ֽ����еĴ洢��ʽ��Ŀǰ��������Ӫ���Ǿ���Motorola��PowerPCϵ��CPU��Intel��x86ϵ��CPU
		* PowerPCϵ�в���big endian��ʽ�洢����
		* x86ϵ�������little endian��ʽ�洢����
		
	# C/C++���Ա�д�ĳ��������ݴ洢˳���Ǹ�����ƽ̨���ڵ�CPU��صģ���JAVA��д�ĳ�����Ψһ����big endian��ʽ���洢����

	# 0x1234abcd д�뵽��0x0000��ʼ���ڴ��� ����Ϊ
		address	big-endian	little-endian
		0x0000	0x12		0xcd
		0x0001	0x34		0xab
		0x0002	0xab		0x34
		0x0003	0xcd		0x12

		int val = 0x1234ABCD;
		ByteBuffer buffer = ByteBuffer.allocate(4);
		// ����С��ģʽ
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		// д��int
		buffer.putInt(val);
		byte[] bytes = buffer.array();
		for (byte b : bytes) {
			System.out.print(Integer.toHexString(b & 0xFF));  // cdab3412
		}
	
	# Little-Endian
		* �������ֽڴ洢����ʼ��ַ����λ��ַ��

	# Big-Endian
		* �������ֽڴ洢����ʼ��ַ����λ��ַ��
		* ��������Э��Ҳ���ǲ���big endian�ķ�ʽ���������ݵġ�

		* ������ʱҲ���big endian��ʽ��֮Ϊ�����ֽ���
		* ����̨���ò�ͬ�ֽ��������ͨ��ʱ���ڷ�������֮ǰ�����뾭���ֽ����ת����Ϊ�����ֽ�����ٽ��д��䡣
	

	# ������big endian������little endian��������Զ���ֽڵ����ж��Եģ�������λ�����ֽڣ�
