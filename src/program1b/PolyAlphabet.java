package program1b;

public class PolyAlphabet {
	
	private static int PATTERN_LENGTH = 5;
	private static int LETTERS_IN_ALPHA = 26;
	static int[] pattern = new int[] {0, 1, 1, 0, 1};
	static int[] patternValues = new int[] {5, 19};

	public static String convert(String string) {
		
		StringBuilder sb = new StringBuilder();
		int letterCount = -1; /* Initialized to -1 because count is 0-based to line up with pattern array index. */
		int incrementAmt;
		for (int i = 0; i < string.length(); i++) {
			int charASCII = (int) string.charAt(i);
			if ((charASCII >= 65 && charASCII <= 90) 					/* Checking to see if character is a letter. 		*/
				|| (charASCII >= 97 && charASCII <= 122)) {				/* Letters are being counted to determine    		*/
				letterCount++;											/* Caesar Cipher value.					     		*/
				int patternIndex = letterCount % PATTERN_LENGTH; 		/* Appropriate value is based on		     		*/
				incrementAmt = patternValues[pattern[patternIndex]]; 	/* C1 C2 C2 C1 C2 pattern represented in array.	    */ 
			} else {
				incrementAmt = 0;
			}
			char newChar;
			if (Character.isUpperCase(string.charAt(i))) {				/* Checks case of letter to determine which ASCII   */
				charASCII = charASCII + incrementAmt;					/* range to check for running out of bounds. 		*/
				if (charASCII > 90) {									/* Whenever a value is out of bounds, subtract 26   */
					charASCII = charASCII - LETTERS_IN_ALPHA;			/* to move value to beginning of alphabet.			*/
				} 
			} else if (Character.isLowerCase(string.charAt(i))) {
				charASCII = charASCII + incrementAmt;
				if (charASCII > 122) {
					charASCII = charASCII - LETTERS_IN_ALPHA;
				}
			}
			newChar = (char) charASCII;
			sb.append(newChar);
		}
		String result = sb.toString();
		return result;
	}
}
