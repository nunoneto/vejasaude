var express = require('express');
var app = express();
var request = require('request');
console.log(__dirname);

app.use('/bo', express.static(__dirname));


app.all('/api**', function (req, res) {
    console.log(req.originalUrl);
    var url = 'http://localhost:8082'+req.originalUrl;
    console.log("redirect to "+url);
    try{
        req.pipe(request(url)).pipe(res);
    }catch(ex){
        console.log(ex);
    } 
});

app.listen(81, function () {
  console.log('starting dev server!');
});

