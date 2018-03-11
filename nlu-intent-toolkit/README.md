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