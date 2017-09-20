STEP 1 - install python3 + SDK + google-auth-oauthlib
------------------------

```
brew install python3
pip3 install --upgrade google-assistant-sdk
```

deps

```
pip3 list

cachetools (2.0.1)
certifi (2017.7.27.1)
chardet (3.0.4)
click (6.7)
google-assistant-sdk (0.3.3)
google-auth (1.1.0)
google-auth-oauthlib (0.1.1)
idna (2.6)
oauthlib (2.0.4)
pip (9.0.1)
pyasn1 (0.3.5)
pyasn1-modules (0.1.4)
requests (2.18.4)
requests-oauthlib (0.8.0)
rsa (3.4.2)
setuptools (32.2.0)
six (1.11.0)
urllib3 (1.22)
wheel (0.29.0)
```

STEP 2 - [create nlu api client OAuth key](https://developers.google.com/assistant/sdk/develop/grpc/config-dev-project-and-account)
------

- goto https://console.cloud.google.com
- select iam-admin -> Manage Resources at the left bottom
- create a project -> zyx-nlu-assistant

- goto https://console.cloud.google.com/apis/dashboard and enable NLU assistantAPI
- goto https://console.developers.google.com/apis/credentials and create OAuth client key

```
google-oauthlib-tool --client-secrets ~/Downloads/client_secret_435129512078-dc2b7u2nttij3rsk8ctktntdcl3k8ocb.apps.googleusercontent.com.json --scope https://www.googleapis.com/auth/assistant-sdk-prototype --save --headless
```

STEP 3 - audio test 
-------


```
$ openssl version
OpenSSL 0.9.8zh 14 Jan 2016
```


```
pip3 install sounddevice
```

```
pip3 list
cachetools (2.0.1)
certifi (2017.7.27.1)
cffi (1.11.0)
chardet (3.0.4)
click (6.7)
google-assistant-sdk (0.3.3)
google-auth (1.1.0)
google-auth-oauthlib (0.1.1)
idna (2.6)
oauthlib (2.0.4)
pip (9.0.1)
pyasn1 (0.3.5)
pyasn1-modules (0.1.4)
pycparser (2.18)
requests (2.18.4)
requests-oauthlib (0.8.0)
rsa (3.4.2)
setuptools (32.2.0)
six (1.11.0)
sounddevice (0.3.8)
urllib3 (1.22)
wheel (0.29.0)
```

```
googlesamples-assistant-audiotest --record-time 10

googlesamples-assistant-audiotest --record-time 10 --audio-block-size=3200 --audio-flush-size=6400
```

```
pip3 install grpcio

$ pip3 list
cachetools (2.0.1)
certifi (2017.7.27.1)
cffi (1.11.0)
chardet (3.0.4)
click (6.7)
google-assistant-sdk (0.3.3)
google-auth (1.1.0)
google-auth-oauthlib (0.1.1)
grpcio (1.6.0)
idna (2.6)
oauthlib (2.0.4)
pip (9.0.1)
protobuf (3.4.0)
pyasn1 (0.3.5)
pyasn1-modules (0.1.4)
pycparser (2.18)
requests (2.18.4)
requests-oauthlib (0.8.0)
rsa (3.4.2)
setuptools (32.2.0)
six (1.11.0)
sounddevice (0.3.8)
urllib3 (1.22)
wheel (0.29.0)
```

References
----------

https://github.com/zyx-machine/assistant-sdk-python/tree/master/google-assistant-sdk
