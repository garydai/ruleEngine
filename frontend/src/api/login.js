import request from '@/utils/request'

export function login(username, password) {
  return request.jsonPost(process.env.GATEWAY_API + '/account-service/sally/v1/account/login',
    {
      username,
      password
    }
  )
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
