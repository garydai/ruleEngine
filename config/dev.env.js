'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  NECKLACE_API: '"http://10.10.10.200:18080/ecreditpal/rest/necklace"',
  TSHIRT_API: '"http://10.10.10.200:15169/necklace/rule"',
})
