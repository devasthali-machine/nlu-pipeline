APIs and Services
------------------

https://console.cloud.google.com/apis/library

1) Create a project and action package 
https://console.cloud.google.com/apis/library/actions.googleapis.com/


install gactions

```
wget https://dl.google.com/gactions/updates/bin/darwin/amd64/gactions/gactions
mv gactions /usr/local/bin/
chmod +x /usr/local/bin/gactions
```

```
$ ll /usr/local/bin/gactions 
-rwxr-xr-x@ 1 a1353612  184630988  6281568 Oct  6 11:46 /usr/local/bin/gactions
```


2) define actions

```
gactions init

```


gactions update --action_package PACKAGE_NAME --project zxx-nlu-assistant


NLU agent
---------

**1) login https://console.dialogflow.com/api-client/#/login**
**2) create NLU agent that takes userQuery and fulfills the user intent.**

populate data:

- agent-name: customer-support
- agent-desc:
- agent language:
- agent timezone:


Then in general settings, I will get client_access_token and dev_access_token.

**3) the agent has 3 important elements**

- user intents
- entities
- data fulfillment

So, lets create user intents and responses.

Click on Intents element. Create a first intent `input.welcome`.
And in response block, enter text how you want to respond.

eg. 

contxt: hi, tell me about transportation rules.
action: input.welcome
fulfillment response: Hi! please find the transportation rules here https://www.dotm.gov.np/en/driving-license/

And save the context info.

**4) Ready to use chatbot. Send a query=...**

```
$ curl 'https://api.dialogflow.com/v1/query?v=20170712&query=please%20tell%20me%20transportation%20rules&lang=en&sessionId=aff7e0a6-7747-4318-8425-fff029750767&timezone=America/Los_Angeles' -H 'Authorization:Bearer b836b72685814aeda040c6d4384e59a1'
{
  "id": "8bc8be10-8a30-46b7-96e3-66e0af97567f",
  "timestamp": "2017-11-12T01:47:36.794Z",
  "lang": "en",
  "result": {
    "source": "agent",
    "resolvedQuery": "please tell me transportation rules",
    "action": "input.welcome",
    "actionIncomplete": false,
    "parameters": {},
    "contexts": [],
    "metadata": {
      "intentId": "1a69a914-0d25-47f5-8efd-2e0993906b8d",
      "webhookUsed": "false",
      "webhookForSlotFillingUsed": "false",
      "intentName": "Welcome Intent"
    },
    "fulfillment": {
      "speech": "Hello!",
      "messages": [
        {
          "type": 0,
          "speech": "Good day!"
        },
        {
          "type": 0,
          "speech": "Hi! please find the transportation rules here https://www.dotm.gov.np/en/driving-license/"
        }
      ]
    },
    "score": 0.8199999928474426
  },
  "status": {
    "code": 200,
    "errorType": "success"
  },
  "sessionId": "aff7e0a6-7747-4318-8425-fff029750767"
}

```

**5) add a fallback intent in case user query does not match defined contexts.**

- action: input.unknown
- response: Sorry, what was that?

**6) add office.hours intent**

context: "office hours", "office-hours"
intent-action-name: office.hours
text-response: Here are office hours;
custome payload:
{
  "Sun": "10AM-4PM",
  "Mon": "10AM-4PM"
}

```
curl 'https://api.dialogflow.com/v1/query?v=20170712&query=what%20are%20office%20hours&lang=en&sessionId=aff7e0a6-7747-4318-8425-fff029750767&timezone=America/Los_Angeles' -H 'Authorization:Bearer client_access_token'
{
  "id": "9d39207c-c9db-48a8-873d-1301f9b38073",
  "timestamp": "2017-11-12T02:41:10.523Z",
  "lang": "en",
  "result": {
    "source": "agent",
    "resolvedQuery": "what are office hours",
    "action": "office.hours",
    "actionIncomplete": false,
    "parameters": {},
    "contexts": [],
    "metadata": {
      "intentId": "cfcfb04d-2011-4ad0-b130-28a1933cebc9",
      "webhookUsed": "false",
      "webhookForSlotFillingUsed": "false",
      "intentName": "office hours"
    },
    "fulfillment": {
      "speech": "Here are office hours",
      "messages": [
        {
          "type": 0,
          "speech": "Here are office hours"
        },
        {
          "type": 4,
          "payload": {
            "Sun": "10AM-4PM",
            "Mon": "10AM-4PM"
          }
        }
      ]
    },
    "score": 0.6399999856948853
  },
  "status": {
    "code": 200,
    "errorType": "success"
  },
  "sessionId": "aff7e0a6-7747-4318-8425-fff029750767"
}

```


[add fulfillment REST api](fulfillment-api/README.md)
---------------------------

add [fulfillemnt api to nlu-agent](https://dialogflow.com/docs/getting-started/basic-fulfillment-conversation)
to nlu-agent 

- goto https://console.dialogflow.com/api-client
- select fulfillment of project
- add fulfillment webhook https://us-central1-customer-support-610a3.cloudfunctions.net/fulfillmentHttp
- save the changes
- goto context intents and enable "Use fulfillment-hook" and "Use fulfillment-hook for slot-filling", save the changes

now test sending query to nlu-agent which will trigger fulfillment-hook and respond back

```
curl 'https://api.dialogflow.com/v1/query?v=20170712&query=Transportation%20rules&lang=en&sessionId=aff7e0a6-7747-4318-8425-fff029750767&timezone=America/Los_Angeles' -H 'Authorization:Bearer client_access_token'
{
  "id": "67db733e-d2cc-4951-91e2-8301e03d4e90",
  "timestamp": "2017-11-12T11:03:00.279Z",
  "lang": "en",
  "result": {
    "source": "agent",
    "resolvedQuery": "Transportation rules",
    "action": "input.welcome",
    "actionIncomplete": false,
    "parameters": {},
    "contexts": [],
    "metadata": {
      "intentId": "9395a7c0-b53f-4c30-8a81-368891a1fe2a",
      "webhookUsed": "true",
      "webhookForSlotFillingUsed": "true",
      "webhookResponseTime": 64,
      "intentName": "WelcomeIntent"
    },
    "fulfillment": {
      "speech": "You can find transportation rules here https://www.dotm.gov.np/en/driving-license/",
      "displayText": "You can find transportation rules here https://www.dotm.gov.np/en/driving-license/",
      "messages": [
        {
          "type": 0,
          "speech": "You can find transportation rules here https://www.dotm.gov.np/en/driving-license/"
        }
      ]
    },
    "score": 1.0
  },
  "status": {
    "code": 200,
    "errorType": "success"
  },
  "sessionId": "aff7e0a6-7747-4318-8425-fff029750767"
}
```

Request to fulfillment-hook looks as;

```
{
  "id": "30d2e4a5-c318-4aac-9d7c-19b57aef2708",
  "timestamp": "2017-11-12T11:08:42.008Z",
  "lang": "en",
  "result": {
    "source": "agent",
    "resolvedQuery": "Transportation rules",
    "speech": "",
    "action": "input.welcome",
    "actionIncomplete": false,
    "parameters": {},
    "contexts": [],
    "metadata": {
      "intentId": "9395a7c0-b53f-4c30-8a81-368891a1fe2a",
      "webhookUsed": "true",
      "webhookForSlotFillingUsed": "true",
      "intentName": "WelcomeIntent"
    },
    "fulfillment": {
      "speech": "",
      "messages": []
    },
    "score": 0.9200000166893005
  },
  "status": {
    "code": 200,
    "errorType": "success"
  },
  "sessionId": "aff7e0a6-7747-4318-8425-fff029750767"
}
```
