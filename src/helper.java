import java.util.Random;

public class helper {
	
	public static String unique_id() {
		Random rand = new Random();
		char ar[] = new char[10];
		 	int minValue = 65; // ASCII value for 'A'
	        int maxValue = 90; // ASCII value for 'Z'
	        
	        for (int i = 0; i < 10; i++) {
	        	   int randomValue;
	             randomValue = rand.nextInt(maxValue - minValue + 1) + minValue;

	        ar[i] = (char) randomValue;
	        }
		
		return new String(ar);
	}
	public static String unique_captcha() {
		int minValue = 48; // ASCII value for '0'
        int maxValue = 90; // ASCII value for 'Z'
        char ar[] = new char[5];
        
		Random random = new Random();
        for (int i = 0; i < ar.length; i++) {
        	   int randomValue;
        do {
             randomValue = random.nextInt(maxValue - minValue + 1) + minValue;
        } while (randomValue > 57 && randomValue < 65); // Exclude symbols

        ar[i] = (char) randomValue;
        
        }
        return new String(ar);
	}
	
	public static boolean correct_name(String s) {
		if (s.matches("[a-zA-Z]+")) {		
		return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean correct_num(String s) {
		if(s.length()<10) {
			return false;
		}
		if (s.matches("[0-9]+")) {		
		return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean correct_num_email(String s) {
		if( correct_num(s) || correct_email(s) ) {
			return true;
		}else
			return false;
		
	}
	
	public static boolean correct_pass(String password) {
		
		boolean hasUppercase = password.matches(".*[A-Z].*");
	    boolean hasLowercase = password.matches(".*[a-z].*");
	    boolean hasSymbol = password.matches(".*[^a-zA-Z0-9].*");
	    boolean hasDigit = password.matches(".*[0-9].*");

	    return hasUppercase && hasLowercase && hasSymbol && hasDigit;
	} 
	
	public static boolean correct_email(String s) {
	    int at = s.indexOf("@");
	    int dot = s.lastIndexOf(".");
	    
	    if (at != -1 && dot != -1 && at < dot) {
	        if (at > 0 && dot - at > 1) {
	            if (dot < s.length() - 1) {
	                return true;
	            }
	        }
	    }
	    
	    return false;
	}
	
	
	
	public static void main(String args[]) {

}}
