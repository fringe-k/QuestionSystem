package qs.common;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * MD5加密算法的工具类
 */
public class MD5Util {
    private static final String salt = "&%5123***&&%%$$#@";

    public static String encrypt(String plainText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        plainText += salt;
        md5.update(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(md5.digest());
    }
}
