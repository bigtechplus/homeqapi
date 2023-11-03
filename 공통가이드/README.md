# BigTechPlus API Service Guide

<br/>

### 문서 이력

| 버전    | 변경일        | 작업자  | 변경내역 |
| ----- | ---------- | ---- | ---- |
| 1.0.0 | 2023-07-12 | 박종승  | 최초작성 |
| 1.0.1 | 2023-10-13 | 홍민지  | 인증변경 |

<br/>

## 공통

### 인증키 발급

- #### Signature 생성

  ​	- **_Java_**

```java
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
```

> 예제에 Hex 인코딩 함수가 포함되어 있지만 org.apache.commons.codec.binary.Hex 와 같은 범용라이브러리 사용을 추천드립니다.


<br/>

​		- **_JavaScript_**

```javascript
const crypto = require('crypto');

function hexEncode(data) {
  if (data === null) {
    return null;
  }

  const length = data.length;
  const encoded = new Array(length * 2);

  for (let i = 0, j = 0; i < length; i++) {
    encoded[j++] = _HEX_LOWER[(0xF0 & data[i]) >>> 4];
    encoded[j++] = _HEX_LOWER[0x0F & data[i]];
  }

  return encoded.join('');
}

const _HEX_LOWER = [
  '0', '1', '2', '3', '4', '5', '6', '7',
  '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
];

const _BTP_CHARSET = "UTF-8";
const _BTP_ALGORITHM = "sha256"; // The algorithm is different in 'crypto'
const _BTP_TIMESTAMP = "{BTP_TIMESTAMP}";
const _BTP_ACCESS_KEY = "{BTP_ACCESS_KEY}";
const _BTP_SECRET_KEY = "{BTP_SECRET_KEY}";

function mac(charset, algorithm, timestamp, accessKey, secretKey, url) {
  timestamp = String(new Date().getTime());

  const message = [url, timestamp, accessKey].join(' ');

  const secretKeySpec = Buffer.from(secretKey, charset);
  const mac = crypto.createHmac(algorithm, secretKeySpec);
  mac.update(Buffer.from(message, charset));

  return mac.digest();
}

function macHex(charset, algorithm, timestamp, accessKey, secretKey, url) {
  const macResult = mac(charset, algorithm, timestamp, accessKey, secretKey, url);
  return hexEncode(Array.from(macResult));
}

try {
  const url = "/rest/v1/service?param0=&param1=";
  const signature = macHex(
    _BTP_CHARSET,
    _BTP_ALGORITHM,
    _BTP_TIMESTAMP,
    _BTP_ACCESS_KEY,
    _BTP_SECRET_KEY,
    url
  );

  console.log(`charset=${_BTP_CHARSET}`);
  console.log(`algorithm=${_BTP_ALGORITHM}`);
  console.log(`accessKey=${_BTP_ACCESS_KEY}`);
  console.log(`secretKey=${_BTP_SECRET_KEY}`);
  console.log(`timestamp=${_BTP_TIMESTAMP}`);
  console.log(`signature=${signature}`);
} catch (e) {
  console.error(e);
}
```






<br/><br/>
### HTTP Request Header

| 이름                     | 타입   | 필수   | 설명                                       |
| :--------------------- | :--- | ---- | ---------------------------------------- |
| **x_btp_timestamp**    | Text | O    | - 1970년 1월 1일 00:00:00 협정 세계시(UTC)부터의 경과 시간을 밀리초(Millisecond)로 나타낸 것<br />- API 서버와 300,000ms 이상 차이가 나는 경우 Http Status Code 401 반환 |
| **x-btp-access-key**   | Text | O    | - 사용자 포털에서 발급받거나 담당자로 부터 전달받은 Access Key ID |
| **x-btp-signature-v1** | Text | O    | - Access Key ID의 Secret Key로 암호화한 서명값 - HMAC 인증 |

<br/><br/>

### Http Status Code

| 상태코드                            | 설명                                       |
| :------------------------------ | :--------------------------------------- |
| **200** _OK_                    | 요청 성공                                    |
| **400** _Bad Request_           | Request Parameter 필수, 데이터타입, 길이, 인코딩 등 오류 |
| **401** _Unauthorized_          | 전체 서비스 접근권한 없음 - 계정 접근권한 확인              |
| **403** _Forbidden_             | 해당 서비스 접근권한 없음 - API 접근권한 확인             |
| **404** _Not Found_             | 서비스 없음                                   |
| **405** _Method Not Allowed_    | 허용되지 않는 메소드                              |
| **500** _Internal Server Error_ | 시스템 오류 - 관리자 문의                          |
| **502** _Bad Gateway_           | 시스템 오류 - 관리자 문의                          |
| **503** _Service Unavailable_   | 시스템 오류 - 관리자 문의                          |
| **504** _Gateway Timeout_       | 시스템 오류 - 관리자 문의                          |

<br/><br/>

### Http Response

<table style="border: 2px;">
  <tr>
    <th>구분</th>
    <th>이름</th>
    <th>타입</th>
    <th>필수</th>
    <th>설명</th>
  </tr>
  <tr>
    <td>Header</td>
    <td>Content-Type</td>
    <td>Text</td>
    <td>O</td>
    <td>- application/json;charset=UTF-8<br />- application/octet-stream</td>
  </tr>
  <tr>
    <td rowspan="7">Body</td>
    <td>trace_id</td>
    <td>Text</td>
    <td>X</td>
    <td>디버깅 및 오류추적에 사용되는 ID</td>
  </tr>
  <tr>
    <td>request_time</td>
    <td>Numeric</td>
    <td>X</td>
    <td>서버수신시간 (단위: ms)</td>
  </tr>
  <tr>
    <td>response_time</td>
    <td>Numeric</td>
    <td>X</td>
    <td>서버수신시간 (단위: ms)</td>
  </tr>
  <tr>
    <td>elapsed_time</td>
    <td>Numeric</td>
    <td>X</td>
    <td>소요시간(단위:ms) = request_time - response_time</td>
  </tr>
  <tr>
    <td>error_code</td>
    <td>Text</td>
    <td>O</td>
    <td>결과코드<br />- S000: 성공<br />- F999: 예외 처리 되지 않은 오류</td>
  </tr>
  <tr>
    <td>error_message</td>
    <td>Text</td>
    <td>O</td>
    <td>오류메시지</td>
  </tr>
  <tr>
    <td>data</td>
    <td>Object</td>
    <td>O</td>
    <td>결과데이터</td>
  </tr>
</table>
<br/>

* _응답예제_

```json
{
    "trace_id": "1697176016918ermDDOl",
    "request_time": 1697176016919,
    "response_time": 1697176016930,
    "elapsed_time": 92,
    "error_code": "S000",
    "error_message": "성공",
    "data": {
        ...
    }
}
```

