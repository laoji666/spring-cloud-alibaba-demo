import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/product/getList',
    method: 'get',params
  })
}
