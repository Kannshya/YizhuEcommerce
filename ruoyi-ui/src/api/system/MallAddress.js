import request from '@/utils/request'

// 查询收货地址列表
export function listMallAddress(query) {
  return request({
    url: '/system/MallAddress/list',
    method: 'get',
    params: query
  })
}

// 查询收货地址详细
export function getMallAddress(id) {
  return request({
    url: '/system/MallAddress/' + id,
    method: 'get'
  })
}

// 新增收货地址
export function addMallAddress(data) {
  return request({
    url: '/system/MallAddress',
    method: 'post',
    data: data
  })
}

// 修改收货地址
export function updateMallAddress(data) {
  return request({
    url: '/system/MallAddress',
    method: 'put',
    data: data
  })
}

// 删除收货地址
export function delMallAddress(id) {
  return request({
    url: '/system/MallAddress/' + id,
    method: 'delete'
  })
}
