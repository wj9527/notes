import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 
 * ����ʱ������
 * @author Administrator
 *
 */
public class PlayingTimeUtils {
	
	// һСʱ60����
	private static final int MINUTES_PER_HOUR = 60;
	
	// һ����60��
	private static final int SECONDS_PER_MINUTE = 60;
	
	// һСʱ3600��
	private  static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
	
	/**
	 * �Ѳ���ʱ����ʽת��Ϊ��
	 * @param expression	HH:mm:ss�����999:59:59����С��00:00:00
	 * @return
	 */
	public static int encodeToSeconds(String expression) {
		String[] results = expression.split(":");
		if (results.length > 0 && results.length < 4) {
			
			int flag = 0;
			
			int seconds = 0;
			int minute = 0;
			int hour = 0; 
			
			for (int i = results.length - 1;  i >= 0; i --) {
				if (flag == 0) {
					seconds = parseTimeInt(results[i], 59);
				} else if (flag == 1) {
					minute = parseTimeInt(results[i], 59);
				} else if (flag == 2) {
					hour = parseTimeInt(results[i], 999);
				}
				
				flag ++;
			}
			
			int total = hour * SECONDS_PER_HOUR;
	        total += minute * SECONDS_PER_MINUTE;
	        total += seconds;
	        return total;
		}
		throw new IllegalArgumentException("����ʱ�����ʽ���Ϸ��������ǣ�HH:mm:ss�����磨01:21:15�������Ҳ��ܴ��ڣ�999:59:59");
	}

	
	private static int parseTimeInt(String val, int max) {
		try {
			int retVal = Integer.parseInt(val);
			if (retVal < 0) {
				throw new IllegalArgumentException("���ʽ�е���ֵ������Ϊ����");
			} 
			if (retVal > max) {
				throw new IllegalArgumentException("���ʽ�е���ֵ�����ܴ��ڣ�" + max);
			}
			return retVal;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("���ʽ�д��ڷ���ֵ���ַ�");
		}
	}
	
	/**
	 * ���룬ת��Ϊ����ʱ���ַ���
	 * @param seconds
	 * @return
	 */
	public static String formart(int seconds) {
		int hour = 0;
		int minte = 0;
		while (seconds >= 60) { // ÿ�ε���1����
			minte ++;
			if (minte == 60) {
				minte = 0;
				hour ++;
			}
			seconds -= 60;
		}
		return Stream.of(hour, minte, seconds).map(i -> i > 9 ? "" + i : "0" + i).collect(Collectors.joining(":"));
	}
	
	public static void main(String[] args) {
		int seconds = encodeToSeconds("01:59");
		System.out.println(seconds);
		System.out.println(formart(seconds));
	}
}
