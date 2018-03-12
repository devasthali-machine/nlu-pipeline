intent-toolkit
---------------


usage

```
sbt "run upd-app \"where is pt playging\" src/test/resources/creds.json"
```

using jar
---------

```
sbt assembly
java -jar -Djavax.net.debug=ssl target/scala-2.12/nlu-intent-toolkit-assembly-0.1.jar upd "where is pt playing" src/test/resources/creds.json
```

must have java8


```
$ openssl s_client -connect dialogflow.googleapis.com:443
CONNECTED(00000003)
depth=2 OU = GlobalSign Root CA - R2, O = GlobalSign, CN = GlobalSign
verify return:1
depth=1 C = US, O = Google Trust Services, CN = Google Internet Authority G3
verify return:1
depth=0 C = US, ST = California, L = Mountain View, O = Google Inc, CN = *.googleapis.com
verify return:1
---
Certificate chain
 0 s:/C=US/ST=California/L=Mountain View/O=Google Inc/CN=*.googleapis.com
   i:/C=US/O=Google Trust Services/CN=Google Internet Authority G3
 1 s:/C=US/O=Google Trust Services/CN=Google Internet Authority G3
   i:/OU=GlobalSign Root CA - R2/O=GlobalSign/CN=GlobalSign
---
Server certificate
-----BEGIN CERTIFICATE-----
MIIE3zCCA8egAwIBAgIIWnywZ0itdTkwDQYJKoZIhvcNAQELBQAwVDELMAkGA1UE
BhMCVVMxHjAcBgNVBAoTFUdvb2dsZSBUcnVzdCBTZXJ2aWNlczElMCMGA1UEAxMc
R29vZ2xlIEludGVybmV0IEF1dGhvcml0eSBHMzAeFw0xODAyMjAxNTExMzFaFw0x
ODA1MTUxNDEwMDBaMGoxCzAJBgNVBAYTAlVTMRMwEQYDVQQIDApDYWxpZm9ybmlh
MRYwFAYDVQQHDA1Nb3VudGFpbiBWaWV3MRMwEQYDVQQKDApHb29nbGUgSW5jMRkw
FwYDVQQDDBAqLmdvb2dsZWFwaXMuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A
MIIBCgKCAQEArqEXNSjOYQmLmXyLKGl1NfbRIqvS5IisuIT+K4n/ReG6WW8a5ezu
+6njOv4889h75Z3+qbco3m1elcmyMDnBlEocILTEsvIqZOgOtVp6uAVGVXFxIlWn
rJqDWAi9S1I34wdZqkEdsZ1WU7b1Q4DAv+TTyOeFvIldUdwJPtE0PhiIS+871bam
8YBr3CxaDBC8PmTnV7IuQNjRq/5etw9AywbdkQeCVum1YYkfSZxP8mPDfEz/Xubu
JRALHfAYLWnoewtKhIrDNF5hYQ3oqg6iJrLFEMflnuXlFM50M/EVo+KUsbXMR2bx
giYR2ZbK6jIh9BDF5vMSMWdXRuXLOIQmLwIDAQABo4IBnTCCAZkwEwYDVR0lBAww
CgYIKwYBBQUHAwEwdAYDVR0RBG0wa4IQKi5nb29nbGVhcGlzLmNvbYIVKi5jbGll
bnRzNi5nb29nbGUuY29tghgqLmNsb3VkZW5kcG9pbnRzYXBpcy5jb22CFmNsb3Vk
ZW5kcG9pbnRzYXBpcy5jb22CDmdvb2dsZWFwaXMuY29tMGgGCCsGAQUFBwEBBFww
WjAtBggrBgEFBQcwAoYhaHR0cDovL3BraS5nb29nL2dzcjIvR1RTR0lBRzMuY3J0
MCkGCCsGAQUFBzABhh1odHRwOi8vb2NzcC5wa2kuZ29vZy9HVFNHSUFHMzAdBgNV
HQ4EFgQUi55iN6UhyYQA4B7jvxjlY4DC2ZEwDAYDVR0TAQH/BAIwADAfBgNVHSME
GDAWgBR3wrhQmmd2drEtwobQg6B+pn66SzAhBgNVHSAEGjAYMAwGCisGAQQB1nkC
BQMwCAYGZ4EMAQICMDEGA1UdHwQqMCgwJqAkoCKGIGh0dHA6Ly9jcmwucGtpLmdv
b2cvR1RTR0lBRzMuY3JsMA0GCSqGSIb3DQEBCwUAA4IBAQAVF2t8S0iMWuIh1Iu7
cFpUGMh/lCS7+cEVEQXfLrxS6ahj30r65sutuxXuYtzgSh0TbK05UIVMTm5gRXFv
r9pWCtSgagTsExh3dVqyXER65S/tc6HNz8I6Y07M+Zbgf5G4fqb8YM8p+Vodo4G8
8+6shI4CROQxfetyCjZU0K83MryTgc7frV4hs3WM9uiV4zVBCTKdQ/tnOx36RHWK
FUPqFOXaU8GkaPpPKhIGnd4tssd6NnW7K+4D3ySVF8hn3MuhcHVpXTJel07nLAVB
56xUxslKAPyMSZ7npjO5T9C/27XJxmY8zKjKX8RbFiu47D2uJ8T3C7Qq/UchMUYQ
GvsH
-----END CERTIFICATE-----
subject=/C=US/ST=California/L=Mountain View/O=Google Inc/CN=*.googleapis.com
issuer=/C=US/O=Google Trust Services/CN=Google Internet Authority G3
---
No client certificate CA names sent
Server Temp Key: ECDH, prime256v1, 256 bits
---
SSL handshake has read 3079 bytes and written 373 bytes
---
New, TLSv1/SSLv3, Cipher is ECDHE-RSA-AES128-GCM-SHA256
Server public key is 2048 bit
Secure Renegotiation IS supported
Compression: NONE
Expansion: NONE
SSL-Session:
    Protocol  : TLSv1.2
    Cipher    : ECDHE-RSA-AES128-GCM-SHA256
    Session-ID: A90EAABC884C87B1CA438A4C1995AF8B3832FF6E3C28337B7C504DD8A2824072
    Session-ID-ctx:
    Master-Key: 95CE3A653C75C9A7508DFC88207AF1091F7D111663C4B9EDB6D2041D6EE4E17C246524A0D63D2B31D19074ADBF946D39
    Key-Arg   : None
    Krb5 Principal: None
    PSK identity: None
    PSK identity hint: None
    TLS session ticket lifetime hint: 100800 (seconds)
    TLS session ticket:
    0000 - 00 cc d0 32 a9 f4 0c 18-3c 3d c8 3f 8a 99 fc 4a   ...2....<=.?...J
    0010 - 81 31 47 f2 93 f0 f8 94-27 90 e5 75 c0 a9 97 7e   .1G.....'..u...~
    0020 - fc 18 7e 1c 8a 74 b6 dc-44 11 32 a2 03 d7 f0 30   ..~..t..D.2....0
    0030 - 7e 22 3d 91 07 dc 18 e5-65 ed 9d 94 48 f9 2a 4a   ~"=.....e...H.*J
    0040 - 66 88 6f 54 71 99 93 6b-79 74 f2 65 17 e5 f3 e8   f.oTq..kyt.e....
    0050 - de 0c 89 ae ea 7a 16 f1-9d 6c 5d 4f 2a 87 b2 da   .....z...l]O*...
    0060 - c0 f2 6f e2 d2 8c 9d aa-2e 14 b7 3e cb 69 2c a6   ..o........>.i,.
    0070 - 8a 61 b7 52 a0 b3 e9 30-1b c2 60 5d f4 e9 62 28   .a.R...0..`]..b(
    0080 - e6 c7 9b 2f c3 b6 3f ee-05 95 12 df 16 c6 04 4f   .../..?........O
    0090 - 11 f6 52 95 45 3e 3d db-33 87 5d f9 51 93 c8 ae   ..R.E>=.3.].Q...
    00a0 - 5b ed 01 06 b6 f8 6e cb-a7 81 ba a7 4c 0d be d5   [.....n.....L...
    00b0 - 40 f5 a3 ec 1d a9 f3 68-fa f7 27 42 5c 98 cc 88   @......h..'B\...
    00c0 - 1c 00 e0 05 9a 5e 32 80-cc d1 03 bc 60 8a f7 1d   .....^2.....`...
    00d0 - 8d 25 d7 a7 af                                    .%...

    Start Time: 1520820027
    Timeout   : 300 (sec)
    Verify return code: 0 (ok)
---
read:errno=0

```