package util;

public class FloatUtils {
	public Float FloatParse(String value) {

		if (value.isEmpty()) {
			return 0f;
		}
		return Float.parseFloat(value);
	}
}
