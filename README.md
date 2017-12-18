# Kotlin Sprint Boot 2 OAuth2 POC

## Generate JKS Java KeyStore File

keytool -genkeypair -alias jwttest -keyalg RSA -keypass jkspass -keystore jwt.jks -storepass jkspass

## Export Public Key

keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey
