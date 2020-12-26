const OpenWeatherMapHelper = require("openweathermap-node");
const express = require('express');
const app = express();
const server = require('http').createServer(app);
const io = require('socket.io')(server);
const cors = require('cors');
const helper = new OpenWeatherMapHelper({
  APPID : '',
  units : "metric"
});

helper.getCurrentWeatherByCityID("1835329", (err, currentWeather) => {
  if(err) {
    console.log(err);
  }
  else {
    current = currentWeather.weather[0].main;
    console.log(current);
  }
});

app.use(cors());

app.use(express.static(__dirname + '/'));

app.get('/', function(req, res, next) {
  res.sendFile(__dirname + '/index.html');
});

io.on('connection', function(client) {
  console.log('--- COMPLETE ---');
  console.log('');
  
  client.on('APIclick') function(data) {
    io.emit('APIUpdate', current);
  });
  
  client.on('oneclick') function(data) {
    curr = 'Clear';
    io.emit('clearUpdate', curr);
  });
  
  client.on('twoclick') function(data) {
    curr = 'Clouds';
    io.emit('cloudsUpdate', curr);
  });
  
  client.on('threeclick') function(data) {
    curr = 'Rain';
    io.emit('rainUpdate', curr);
  });
  
  client.on('fourclick') function(data) {
    curr = 'Snow';
    io.emit('snowUpdate', curr);
  });
  
  client.on('GoButtonClick') function(data) {
    curr = 'GO';
    io.emit('goUpdate', curr);
  });
  
  client.on('BackButtonClick') function(data) {
    curr = 'BACK';
    io.emit('backUpdate', curr);
  });
  
  client.on('LeftButtonClick') function(data) {
    curr = 'LEFT';
    io.emit('leftUpdate', curr);
  });
  
  client.on('RightButtonClick') function(data) {
    curr = 'RIGHT';
    io.emit('rightUpdate', curr);
  });
});

server.listen(8080, function() {
  console.log('--- STANDING BY ---');
});
