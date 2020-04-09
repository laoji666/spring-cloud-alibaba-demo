import request from '@/utils/request'

export function info(params) {
  return request({
    url: '/profile/info/'+params,
    method: 'get'
  })
}
export function update(data) {
  return request({
    url: '/profile/update',
    method: 'post',
    data
  })
}
/**
 * 更新头像
 * @param data
 */
export function updateIcon(data) {
  return request({
    url: '/profile/updateIcon',
    method: 'post',
    data
  })
}
/**
 * 更新密
码
* @param data
*/
export function updatePassword(data) {
  return request({
    url: '/profile/updatePassword',
    method: 'post',
    data
  })
}
