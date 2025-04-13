import request from '@/utils/request'

// 查询商品管理列表
export function listMallProduct(query) {
  return request({
    url: '/system/MallProduct/list',
    method: 'get',
    params: query
  })
}

// 查询商品管理详细
export function getMallProduct(id) {
  return request({
    url: '/system/MallProduct/' + id,
    method: 'get'
  })
}

// 新增商品管理
export function addMallProduct(data) {
  return request({
    url: '/system/MallProduct',
    method: 'post',
    data: data
  })
}

// 修改商品管理
export function updateMallProduct(data) {
  return request({
    url: '/system/MallProduct',
    method: 'put',
    data: data
  })
}

// 删除商品管理
export function delMallProduct(id) {
  return request({
    url: '/system/MallProduct/' + id,
    method: 'delete'
  })
}
