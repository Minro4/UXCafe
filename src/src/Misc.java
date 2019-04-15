package src;

import src.Modèles.ComposanteBreuvage;

public class Misc {
	
	public static ComposanteBreuvage[] combine(ComposanteBreuvage[]... as){
        int length = 0;
        for (int i = 0; i < as.length; i++) {
			length+= as[i].length;
		}
        ComposanteBreuvage[] result = new ComposanteBreuvage[length];
        int currentPos = 0;
        for (Object[] a : as) {
        	System.arraycopy(a, 0, result, currentPos, a.length);
        	currentPos+= a.length;
		}
        return result;
    }
}
