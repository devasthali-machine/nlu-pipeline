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


references
----------

https://docs.lexigram.io/v1/lexigraph/getConcept

http://demo.lexigram.io/#!/

