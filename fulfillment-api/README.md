fulfillment REST api
---------------------------

https://dialogflow.com/docs/getting-started/basic-fulfillment-conversation

install gcloud - https://cloud.google.com/sdk/docs/#install_the_latest_cloud_tools_version_cloudsdk_current_version

```
wget https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-sdk-179.0.0-darwin-x86.tar.gz
tar -zxvf google-cloud-sdk-179.0.0-darwin-x86.tar.gz
sudo mv google-cloud-sdk /usr/local/
sudo chmod -R 777 /usr/local/google-cloud-sdk/
google-cloud-sdk/install.sh

source /usr/local/google-cloud-sdk/completion.bash.inc
source /usr/local/google-cloud-sdk/path.bash.inc
```

verify gcloud cli

```
gcloud version
Google Cloud SDK 179.0.0
bq 2.0.27
core 2017.11.06
gsutil 4.28
```

```
sudo chmod -R 707 /usr/local/
gcloud components update
```

* create a storage bucket

```
gcloud auth login

//turn billing on before creating a storage bucket
gsutil mb -p customer-support-610a3 gs://customer-support-bucket

//enable Cloud Functions API
//deploy/redeploy
gcloud beta functions deploy fulfillmentHttp --project customer-support-610a3 --stage-bucket customer-support-bucket --trigger-http
-2 ['./.git', './.gitignore']
-1 [False, False]
-2 ['./.git', './.gitignore']
-1 [False, False]
Copying file:///var/folders/t0/d_6dzlq541v5tm9217k13wyx81n8sq/T/tmpP0cskU/fun.zip [Content-Type=application/zip]...
/ [1 files][  1.4 KiB/  1.4 KiB]                                                
Operation completed over 1 objects/1.4 KiB.                                      
Deploying function (may take a while - up to 2 minutes)...done.                                                                                       
availableMemoryMb: 256
entryPoint: fulfillmentHttp
httpsTrigger:
  url: https://us-central1-customer-support-610a3.cloudfunctions.net/fulfillmentHttp
labels:
  deployment-tool: cli-gcloud
name: projects/customer-support-610a3/locations/us-central1/functions/fulfillmentHttp
serviceAccountEmail: customer-support-610a3@appspot.gserviceaccount.com
sourceArchiveUrl: gs://customer-support-bucket/us-central1-fulfillmentHttp-sfdjtosqygic.zip
status: ACTIVE
timeout: 60s
updateTime: '2017-11-12T08:03:37Z'
versionId: '1'

```

```

//show http function status
//https://console.cloud.google.com/functions/list

gcloud beta functions describe fulfillmentHttp --project customer-support-610a3
availableMemoryMb: 256
entryPoint: fulfillmentHttp
httpsTrigger:
  url: https://us-central1-customer-support-610a3.cloudfunctions.net/fulfillmentHttp
labels:
  deployment-tool: cli-gcloud
name: projects/customer-support-610a3/locations/us-central1/functions/fulfillmentHttp
serviceAccountEmail: customer-support-610a3@appspot.gserviceaccount.com
sourceArchiveUrl: gs://customer-support-bucket/us-central1-fulfillmentHttp-sfdjtosqygic.zip
status: ACTIVE
timeout: 60s
updateTime: '2017-11-12T08:03:37Z'
versionId: '1'


//verify http function by sending a req
gcloud beta functions call fulfillmentHttp --data '{"message":"Hello World!"}' --project customer-support-610a3
executionId: fuododlnakhz
result: '{"speech":"Fulfillment data!","displayText":"Fulfillment data!"}'


//also
curl -XGET https://us-central1-customer-support-610a3.cloudfunctions.net/fulfillmentHttp
{"speech":"Fulfillment data!","displayText":"Fulfillment data!"}
```


```
//logs
gcloud beta functions logs read fulfillmentHttp --project customer-support-610a3
LEVEL  NAME             EXECUTION_ID  TIME_UTC                 LOG
D      fulfillmentHttp  fuododlnakhz  2017-11-12 08:06:17.141  Function execution started
D      fulfillmentHttp  fuododlnakhz  2017-11-12 08:06:17.204  Function execution took 63 ms, finished with status code: 200
D      fulfillmentHttp  fuodrsmlrgmc  2017-11-12 08:13:29.057  Function execution started
D      fulfillmentHttp  fuodrsmlrgmc  2017-11-12 08:13:29.069  Function execution took 13 ms, finished with status code: 200
```

