import request from '@/utils/request'

export function addScene(data) {
  return request.formPost('/scene/add', data)
}

export function getSceneList(data) {
  return request({
    url: process.env.NECKLACE_API + '/scene/list',
    method: 'get',
    params: data
  })
}

export function updateScene(id, data) {
  return request.formPost(process.env.NECKLACE_API + '/scene/update/' + id, data)
}
