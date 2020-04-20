import request from '@/utils/request'

export function getUserList(params) {
  return request({
    url: '/system/user/getAll',
    method: 'get',params
  })
}
export function getRoleList() {
  return request({
    url: '/system/user/getRoleList',
    method: 'get'
  })
}
export function getRoleByAdminId(params) {
  return request({
    url: '/system/user/getRoleByAdminId',
    method: 'get',params
  })
}
export function updateRole(data,id) {
  return request({
    url: '/system/user/updateRole?id='+id,
    method: 'post',
    data
  })
}
export function addAdmin(data) {
  return request({
    url: '/system/user/addAdmin',
    method: 'post',
    data
  })
}
