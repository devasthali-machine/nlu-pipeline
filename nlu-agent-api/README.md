NLU agent 
-----------

```
sbt run
```

```bash
curl -XPOST -H "Content-Type: application/json" -d '{"authToken": "a", "correlationID": "c494cc73-371c-4efa-845c-8727cd9b6079", "msgTimestamp": "2017-11-30T01:54:41.802", "name": "some name", "passthrough": null, "userID": "userID", "loyalty": 0}' localhost:9191/auth 

{"response":"some response","conversationID":"5124def8-7b25-4e92-80a6-b179096beac7","passthrough":{"userToken":"user-token","authToken":"auth","chatbotVersion":"1.0","contents":"welcome to nlu","clientChannel":"channel"},"msgTimestamp":"2017-11-30T01:57:10.725","correlationID":"c494cc73-371c-4efa-845c-8727cd9b6079"}
```

```bash
/connect ws://localhost:9191/auth
/send play porcupine tree
/send what is weather outside
/send I'm feeling excited
```

TODOs
-----

- websocket supported nlu-agent
- docker deployment
-

```
curl -i \
     --no-buffer \
     -H "Connection: Upgrade" \
     -H "Upgrade: websocket" \
     -H "Host: localhost:9191" \
     -H "Origin: http://localhost:9191" \
     -H "Sec-WebSocket-Key: SGVsbG8sIHdvcmxkIQ==" \
     -H "Sec-WebSocket-Version: 13" localhost:9191/chat

HTTP/1.1 400 Bad Request
Server: akka-http/10.0.10
Date: Mon, 04 Dec 2017 08:48:19 GMT
Content-Type: text/plain; charset=UTF-8
Content-Length: 34

Expected WebSocket Upgrade request
```

references
----------

https://docs.lexigram.io/v1/lexigraph/getConcept

http://demo.lexigram.io/#!/

https://medium.com/@brijrajsingh/chat-bots-designing-intents-and-entities-for-your-nlp-models-35c385b7730d

https://nlp.stanford.edu/IR-book/html/htmledition/stemming-and-lemmatization-1.html

```
The goal of both stemming and lemmatization is to reduce inflectional forms and sometimes derivationally
related forms of a word to a common base form. For instance:

am, are, is => be
car, cars, car's, cars' => car
```

http://snowballstem.org/

```
a small string processing language designed for creating
stemming algorithms for use in Information Retrieval
```