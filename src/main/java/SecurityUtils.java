import android.annotation.SuppressLint;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class SecurityUtils
{
  public static final String TAG = "SecurityUtils";
  private static String a = new String("idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#");
  
  @SuppressLint({"TrulyRandom"})
  private static byte[] a(byte[] paramArrayOfByte) throws NoSuchAlgorithmException, NoSuchProviderException {
    KeyGenerator localKeyGenerator = KeyGenerator.getInstance("AES");
    SecureRandom localSecureRandom = SecureRandom.getInstance("SHA1PRNG");
    ReflectUtil.invokeMethod(localSecureRandom, ReflectUtil.getSetter(new String(Base64Util.decode("c2VlZA==")), SecureRandom.class, paramArrayOfByte.getClass()), new Object[] { paramArrayOfByte });
    localKeyGenerator.init(128, localSecureRandom);
    return localKeyGenerator.generateKey().getEncoded();
  }
  

  public static String decrypt(String str, String str2) {
    try {
      byte[] a = a(str.getBytes());
      byte[] toByte = toByte(str2);
      SecretKeySpec secretKeySpec = new SecretKeySpec(a, "AES");
      Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
      instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
      return new String(instance.doFinal(toByte));
    } catch (Exception e) {
      return null;
    }
  }


  public static String encrypt(String str, String str2) {
    byte[] bArr = null;
    try {
      byte[] a = a(str.getBytes());
      byte[] bytes = str2.getBytes();
      SecretKeySpec secretKeySpec = new SecretKeySpec(a, "AES");
      Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
      instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
      bArr = instance.doFinal(bytes);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return toHex(bArr);
  }


  
  public static String fromHex(String paramString)
  {
    return new String(toByte(paramString));
  }
  
  public static String getSeed()
  {
    String str = new String();
    int i = 0;
    while (i < a.length() - 1)
    {
      str = str + a.charAt(i);
      i += 4;
    }
    return str;
  }
  
  public static byte[] toByte(String paramString)
  {
    int j = paramString.length() / 2;
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    while (i < j)
      {
          arrayOfByte[i] = Integer.valueOf(paramString.substring(i * 2, i * 2 + 2), 16).byteValue();
          i += 1;
      }
      return arrayOfByte;
  }
  
  public static String toHex(String paramString)
  {
    return toHex(paramString.getBytes());
  }
  
  public static String toHex(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i];
      localStringBuffer.append("0123456789ABCDEF".charAt(j >> 4 & 0xF)).append("0123456789ABCDEF".charAt(j & 0xF));
      i += 1;
    }
    return localStringBuffer.toString();
  }

  public static void  main(String[] args){
      //80860CB51DD278D2CEA8AE0A1737E858
      //80860CB51DD278D2CEA8AE0A1737E858
    String res=SecurityUtils.encrypt(SecurityUtils.getSeed(),"11");
    String res2=SecurityUtils.decrypt(SecurityUtils.getSeed(),res);
    System.out.println(res);
    System.out.println(res2);
    System.out.println(SecurityUtils.getSeed());
  }


}


/* Location:              /Users/mac/Desktop/ApkTool,dex2jar,JD-GUI for Mac/classes-dex2jar.jar!/com/alipay/security/mobile/module/commonutils/crypto/SecurityUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */