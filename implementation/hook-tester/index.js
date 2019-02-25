const express = require('express');
const bodyParser = require('body-parser');
const app = express();

app.use(bodyParser.json())

app.post('/', function(req, res){
    console.dir(req.body);

  res.setHeader('Content-Type', 'text/plain')
  res.write('you posted:\n')
  res.end(JSON.stringify(req.body, null, 2))
});

app.listen(3000, () => console.log('Getting all post on port :3000/'));
