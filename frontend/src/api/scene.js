import request from '@/utils/request'

export function addScene(data) {
  return request.formPost(process.env.GATEWAY_API + '/scene/add', data)
}

export function getSceneList(data) {
  return request({
    url: process.env.GATEWAY_API + '/scene/list',
    method: 'get',
    params: data
  })
}

export function updateScene(id, data) {
  return request.formPost(process.env.GATEWAY_API + '/scene/update/' + id, data)
}

export function deleteScene(id) {
  return request.formPost(process.env.GATEWAY_API + '/scene/delete/' + id)
}
