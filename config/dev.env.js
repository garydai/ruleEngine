'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  NECKLACE_API: '"http://127.0.0.1:8080/ecreditpal/rest/necklace"',
  TSHIRT_API: '"http://127.0.0.1:5169/necklace/rule"',
})
