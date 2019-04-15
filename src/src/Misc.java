package src;

public class Misc {
	
	public static Object[] combine(Object[]... as){
        int length = 0;
        for (int i = 0; i < as.length; i++) {
			length+= as[i].length;
		}
        Object[] result = new Object[length];
        int currentPos = 0;
        for (Object[] a : as) {
        	System.arraycopy(a, 0, result, currentPos, a.length);
        	currentPos+= a.length;
		}
        return result;
    }
}
