import request from '@/utils/request'

export function getList() {
  return request({
    url: '/table/list',
    method: 'get'
  })
}
