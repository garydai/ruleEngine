import request from '@/utils/request'

export function addScene(data) {
  return request.formPost('/necklace/scene/add', data)
}

export function getSceneList(data) {
  return request({
    url: '/necklace/scene/list',
    method: 'get',
    params: data
  })
}

export function updateScene(id, data) {
  return request.formPost('/necklace/scene/update/' + id, data)
}
