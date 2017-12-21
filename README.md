# Kotlin Sprint Boot 2 OAuth2 POC

## Generate JKS Java KeyStore File

keytool -genkeypair -alias jwttest -keyalg RSA -keypass jkspass -keystore jwt.jks -storepass jkspass

## Export Public Key

keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey

## Usage

curl -X POST http://localhost:8080/oauth/token -H 'authorization: Basic Q0xJRU5UOjEyMzQ=' -H 'cache-control: no-cache' -H 'content-type: application/x-www-form-urlencoded' -H 'postman-token: 5cf186ea-19da-a9b5-f106-0445edc1fb8b' -d 'username=USER&password=1234&grant_type=password'
