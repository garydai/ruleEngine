import request from '@/utils/request'

export function getList(id) {
  return request({
    url: process.env.GATEWAY_API + '/drl/list',
    method: 'get',
    params: { sceneId: id }
  })
}

export function getVariables() {
  return request({
    url: process.env.TSHIRT_API + '/variables',
    method: 'get'
  })
}

export function getActions() {
  return request({
    url: process.env.TSHIRT_API + '/actions',
    method: 'get'
  })
}

export function getVersion(envId) {
  return request({
    url: process.env.GATEWAY_API + '/version',
    method: 'get',
    params: { env: envId }
  })
}

export function getVersionHistory(id, envId) {
  return request({
    url: process.env.GATEWAY_API + '/version/' + id,
    method: 'get',
    params: { env: envId }
  })
}

// todo refine
export function updateRule(id, data) {
  return request.formPost(process.env.GATEWAY_API + '/drl/update/' + id, data)
}

export function addRule(data) {
  return request.formPost(process.env.GATEWAY_API + '/drl/add', data)
}

export function executeRule(data) {
  return request.formPost(process.env.TSHIRT_API + '/execute', data)
}

export function testRule(data) {
  return request.formPost(process.env.TSHIRT_API + '/test', data)
}

export function deleteDrl(data) {
  return request.formPost(process.env.GATEWAY_API + '/drl/delete', data)
}

export function activateRule(data) {
  return request.formPost(process.env.TSHIRT_API + '/activate', data)
}

export function getDrl(id) {
  return request({
    url: process.env.GATEWAY_API + '/drl/' + id,
    method: 'get'
  })
}

export function insertFlow(data) {
  return request.formPost(process.env.GATEWAY_API + '/workflow/add', data)
}

export function getFlow(data) {
  return request({
    url: process.env.GATEWAY_API + '/workflow/latest',
    method: 'get',
    params: data
  })
}

export function deployTest(flowId) {
  return request.formPost(process.env.GATEWAY_API + '/deploy/test/' + flowId)
}

export function deployProd(flowId) {
  return request.formPost(process.env.GATEWAY_API + '/deploy/prod/' + flowId)
}

