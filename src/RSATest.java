import qs.common.MD5Util;
import qs.common.RSAUtil;

import java.security.MessageDigest;

public class RSATest {
    public static void main(String[] args){
        try {
            if (RSAUtil.generateKey()) {
                System.out.println("-----成功生成公私钥-------");

            }
            RSAUtil.loadKey();
            System.out.println("-----成功从文件加载公私钥-------");
            System.out.println(String.format("公钥字符串是%s",RSAUtil.getKeyString(RSAUtil.getKeys().get("publicKey"))));
            System.out.println(String.format("私钥字符串是%s",RSAUtil.getKeyString(RSAUtil.getKeys().get("privateKey"))));

            String message = "墨凝霜天";
            String encrypted = RSAUtil.encrypt(message);
            System.out.println(String.format("%s 加密后是： %s",message,encrypted));
            System.out.println(String.format("%s 解密后是： %s",encrypted,RSAUtil.decrypt(encrypted)));
            assert message.equals(RSAUtil.decrypt(encrypted));
            System.out.println("测试成功");
            System.out.println(String.format("%s md5编码是 %s",message, MD5Util.encrypt(message)));



        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
