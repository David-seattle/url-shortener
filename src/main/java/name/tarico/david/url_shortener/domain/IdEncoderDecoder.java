package name.tarico.david.url_shortener.domain;


/**
 * Converts between numeric IDs and a base-62 String representation.
 * Strings will be alpha-numeric, and can contain both lower and uppercase letters
 * in the range of A through Z.
 */
public class IdEncoderDecoder {

    //Array of characters for encoding.  The index is base-62 digit.
    private static char[] base62Chars = new char[62];

    //Using an array instead of a map for efficiency.  The index is a char's numeric value
    //and the value is the base-62 digit for that char.
    //Only 62 items in the array are used, and unused items have the value -1.
    private static final char MAX_ENCODING_CHAR = 'z';
    private static int[] reverseMappingOfChars = new int[MAX_ENCODING_CHAR + 1];

    static {
        int arrayIndex = 0;
        for (char i = '0'; i <= '9'; i++) {
            base62Chars[arrayIndex++] = i;
        }
        for (char i = 'a'; i <= 'z'; i++) {
            base62Chars[arrayIndex++] = i;
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            base62Chars[arrayIndex++] = i;
        }

        if (arrayIndex != base62Chars.length) {
            throw new RuntimeException("The array of characters for encoding was not fully populated");
        }


        //Initialize the array since not all buckets will be used, allowing us to tell which
        //ones don't contain a valid base-62 digit.
        for (int i = 0; i < reverseMappingOfChars.length; i++) {
            reverseMappingOfChars[i] = -1;

        }

        for (int charIndex = 0; charIndex < base62Chars.length; charIndex++) {
            char encodingChar = base62Chars[charIndex];
            reverseMappingOfChars[encodingChar] = charIndex;
        }
    }


    public static long decode(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty: '" + string + "'");
        }

        long result = 0;
        boolean isFirst = true;

        for (char encodedChar : string.toCharArray()) {
            if (isFirst) {
                isFirst = false;
            } else {
                result = result * base62Chars.length;
            }

            if (encodedChar >= reverseMappingOfChars.length || reverseMappingOfChars[encodedChar] == -1) {
                throw new IllegalArgumentException("Invalid character: " + encodedChar + " in string: " + string);
            }
            result += reverseMappingOfChars[encodedChar];
        }

        return result;
    }

    public static String encode(long id) {
        if (id <= 0) {
            //Not supporting 0 so that we always produce a string with a least one character in it.
            throw new IllegalArgumentException("ID must be positive: " + id);
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (id > 0) {
            int charIndex = (int) (id % base62Chars.length);
            stringBuilder.append(base62Chars[charIndex]);
            id = id / base62Chars.length;
        }

        return stringBuilder.reverse().toString();

    }

}
