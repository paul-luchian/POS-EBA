package pos.util;

public class StringUtility {
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static long stringToLong(String number) {
		try {
			return Long.parseLong(number);
		} catch (Exception e) {
			return 0;
		}
	}

	public static String cleanString(String string) {
		return string.trim().toLowerCase();
	}
}
