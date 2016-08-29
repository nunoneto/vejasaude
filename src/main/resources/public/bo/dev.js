var express = require('express');  
var request = require('request');

var app = express();  
app.use('/', express.static('./'));

app.use('/api', function(req, res) {  
  var url = "http://localhost:8083/api" + req.url;
  req.pipe(request(url)).pipe(res);
  console.trace("piping url: "+req.url);
});

app.listen(3000);  