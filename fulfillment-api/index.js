/*
* HTTP Cloud Function.
*
* @param {Object} req Cloud Function request context.
* @param {Object} res Cloud Function response context.
*/

exports.fulfillmentHttp = function fulfillmentHttp (req, res) {
  
  console.log(JSON.stringify(req.body))
  intent_action = req.body.result.action

  var responseText = ""

  if(intent_action == "input.welcome") {
    responseText = "You can find transportation rules here https://www.dotm.gov.np/en/driving-license/"
  } else {
    responseText = "Do you want to talk to our support?"
  }

  res.setHeader('Content-Type', 'application/json'); //Requires application/json MIME type
  res.send(JSON.stringify({ "speech": responseText, "displayText": responseText
  //"speech" is the spoken version of the response, "displayText" is the visual version
  }));
};

