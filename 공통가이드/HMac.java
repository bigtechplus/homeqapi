import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMac {

  private static final char[] _HEX_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  public static String hexEncode(byte[] data) {
    
    if(data == null) {
      return null;
    }
    
    
    int length = data.length;
    char[] encoded = new char[length << 1];
    
    for(int i = 0, j = 0; i < length; i++) {
      encoded[j++] = _HEX_LOWER[(0xF0 & data[i]) >>> 4];
      encoded[j++] = _HEX_LOWER[0x0F & data[i]];
    }
    
    return new String(encoded);
  }// end of hex_encode
  
  private static final String _BTP_CHARSET = "UTF-8";
  private static final String _BTP_ALGORITHM = "HmacSHA256";
  private static final String _BTP_TIMESTAMP = "{BTP_TIMESTAMP}";
  private static final String _BTP_ACCESS_KEY = "{BTP_ACCESS_KEY}";
  private static final String _BTP_SECRET_KEY = "{BTP_SECRET_KEY}";
  
  public static byte[] mac(
      String charset
      , String algorithm
      , String timestamp
      , String accessKey
      , String secretKey
      , String url
      ) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
    
    String timestamp = String.valueOf(new Date().getTime());
    
    String message = String.join(" ", Arrays.asList(url, timestamp, accessKey));
    
    SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(charset), algorithm);
    
    Mac mac = Mac.getInstance(algorithm);
    mac.init(secretKeySpec);
    
    return mac.doFinal(message.getBytes(charset));
  }// end of mac
  public static String macHex(
      String charset
      , String algorithm
      , String timestamp
      , String accessKey
      , String secretKey
      , String url
      ) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
    return hexEncode(mac(charset, algorithm, timestamp, accessKey, secretKey, url));
  }// end of macHex
  
  public static void main(String[] args) {
    
    try {
      String url = "/rest/v1/service?param0=&param1=";
      String signature = macHex(
          _BTP_CHARSET//charset
          , _BTP_ALGORITHM//algorithm
          , _BTP_TIMESTAMP// timestamp
          , _BTP_ACCESS_KEY//accessKey
          , _BTP_SECRET_KEY//secretKey
          , url
          );
      System.out.println(String.format("charset=%s", _BTP_CHARSET));
      System.out.println(String.format("algorithm=%s", _BTP_ALGORITHM));
      System.out.println(String.format("accessKey=%s", _BTP_ACCESS_KEY));
      System.out.println(String.format("secretKey=%s", _BTP_SECRET_KEY));
      System.out.println(String.format("timestamp=%s", _BTP_TIMESTAMP));
      System.out.println(String.format("signature=%s", signature));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }// end of main
}