package cinema.Util;

import java.util.Random;

public class TokenUtil {
    private static final String CHARS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1!2@3#4$5%6^7&8*9(0)";

    public static String generateToken() {
        StringBuilder token = new StringBuilder();
        Random rnd = new Random();
        while (token.length() < 64) {
            int index = (int) (rnd.nextFloat() * CHARS.length());
            token.append(CHARS.charAt(index));
        }
        return token.toString();
    }
}
