package com.carshop.server.encrypt_decrypt;

import java.security.*;
import java.security.interfaces.*;
import java.math.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class RSA {
    //公钥(e,n)   私钥(d,n)
    static BigInteger e;
    static BigInteger n;
    static BigInteger d;

    static {
        e = new BigInteger("65537");
        n = new BigInteger("1050425870236151928564847265005213207195384063520109944008851243121423" +
                                "2905467902709578379038640059260086785818153436725715973005347456725854" +
                                "5337432601060609080243348020630126545407046456691194808400240649890593" +
                                "8128131746585090234048642113738345840236753622866653148577386228994453" +
                                "88664714903825559681329180803");
        d = new BigInteger("2118899248443158596766297029673149304838942478254398806749929571702277" +
                                "4770020854451779326318083156601362178390220552285573822898621141937500" +
                                "4861507085466374759269551696721274228527587357735734913414159979350650" +
                                "6776033554002953867392368597681669450220608705742521664632866514197641" +
                                "157971032701720090518331953");
    }

    //加密，需要公钥
    public byte[] encrypt(byte[] ptext) throws Exception {
        // 获取明文m
        BigInteger m = new BigInteger(ptext);
        // 计算密文c
        BigInteger c = m.modPow(e, n);
        return c.toByteArray();
    }

    //使用私钥进行解密
    public byte[] decrypt(byte[] ctext) throws Exception {
        // 读取密文
        BigInteger c = new BigInteger(ctext);
        BigInteger m = c.modPow(d, n);
        // 显示解密结果
        byte[] mt = m.toByteArray();
        return mt;
    }

    public static String passwordEncrypt(String password){
        String str = password;
        try{    //对密码进行RSA加密，获取password
            RSA rsa = new RSA();
            byte[] data = rsa.encrypt(password.getBytes());
            password = Base64.getEncoder().encodeToString(data);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return password;
    }

    public static String tokenEncrypt(String id, String password){

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        String date = sdf.format(d);
        String token = id +'&'+ password + '&' + date;
        try{    //对账号密码字符串token进行RSA加密,获取token
            RSA rsa = new RSA();
            byte[] data= rsa.encrypt(token.getBytes());
            token = Base64.getEncoder().encodeToString(data);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return token;
    }
}
