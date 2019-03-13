import Crypto from 'crypto'

export function encryptMd5(password) {
  let hash = Crypto.createHash('md5');
  hash.update(password)
  let text = hash.digest('hex');//使用CryptoJS方法加密
  return text;
}
