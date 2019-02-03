'use strict';

const express = require('express');
var session = require('express-session');

// Constants
const PORT = 8080;
const HOST = '0.0.0.0';

// App
const app = express();

app.use(express.static(__dirname + '/public'));

var hostname = process.env.NODE_ENV !== undefined ? process.env.BACKEND_HOSTNAME : "localhost";

app.get('/', (req, res) => {	
  res.cookie('backend',hostname, { maxAge: 900000});
  res.sendFile('login.html', { root: __dirname + "/public" } );
});

app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);