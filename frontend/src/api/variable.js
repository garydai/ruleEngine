import request from '@/utils/request'

export function listVariable(token) {
  return request({
    url: process.env.GATEWAY_API + '/variable-service/sally/v1/variables',
    method: 'get',
    params: { token }
  })
}
