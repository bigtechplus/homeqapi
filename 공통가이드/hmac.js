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