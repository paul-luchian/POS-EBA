package pos.util;

import java.util.Date;

public class DateUtility {
	public static long dateToLong(Date date) {
		return date != null ? date.getTime() : 0;
	}
}
