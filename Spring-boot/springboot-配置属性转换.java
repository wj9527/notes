------------------------------
�Զ����������Ե�ת��
------------------------------
	# ��ʹ�� @ConfigurationProperties ��������ע���ʱ�򣬿���ͨ���Զ���Converter�������ݵ�ת��

	# ����
		demo:
		  startTime: "09:30:00"
		  timeout: 2m

  # LocalTimeConverter
	import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
	import org.springframework.core.convert.converter.Converter;
	import org.springframework.stereotype.Component;
	import java.time.LocalTime;
	import java.util.Objects;
	@Component
	@ConfigurationPropertiesBinding
	public class LocalTimeConverter implements Converter<String, LocalTime> {
		@Override
		public LocalTime convert(String source) {
			Objects.requireNonNull(source);
			return LocalTime.parse(source);
		}
	}
	
	*  �Զ��� Converter ʵ���࣬��ʶ @ConfigurationPropertiesBinding ע��

  # ������
	@Component
	@ConfigurationProperties(prefix = "demo")
	public class ConfigProperties {
		private LocalTime startTime;
		private Duration timeout;
	}
