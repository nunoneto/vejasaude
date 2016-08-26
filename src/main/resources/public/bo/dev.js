var express = require('express');  
var request = require('request');

var app = express();  
app.use('/', express.static('./'));

app.use('/api', function(req, res) {  

  var url = "http://localhost:8082/api" + req.url;
  req.pipe(request(url)).pipe(res);
});

app.listen(3000);  