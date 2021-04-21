const express = require('express')
const app = express()
const port = 39393

app.use(express.json())

app.post('/api/course', (req, res) => {
  console.log(req.body)
  res.send('Course')
})

app.post('/api/play', (req, res) => {
  console.log(req.body)
  res.send('Play')
})

app.listen(port, () => {
  console.log(`Listening at http://localhost:${port}`)
})