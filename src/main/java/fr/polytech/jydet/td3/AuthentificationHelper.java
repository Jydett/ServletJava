package fr.polytech.jydet.td3;

import java.util.HashMap;
import java.util.Objects;

public class AuthentificationHelper {
    private static AuthentificationHelper INSTANCE;
    private HashMap<String, String> validAuth;

    public static AuthentificationHelper getInstance() {
        return INSTANCE == null ? INSTANCE = new AuthentificationHelper() : INSTANCE;
    }

    public AuthentificationHelper() {
        validAuth = new HashMap<>();
        validAuth.put("admin", "admin");
    }

    public boolean authenticate(String login, String password) {
        return Objects.equals(validAuth.get(login), password);
    }

    public static boolean authorized(String authorization) {
        if (authorization == null || !authorization.toUpperCase().startsWith("BASIC ")) {
            return false;
        }
        String userpassEncoded = authorization.substring(6);
        sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
        try {
            String userpassDecoded = new String(dec.decodeBuffer(userpassEncoded));

            String[] account = userpassDecoded.split(":");
            if (account.length != 2) {
                return false;
            }
            return getInstance().authenticate(account[0], account[1]);
        } catch (Exception e) {
            return false;
        }
    }
}
