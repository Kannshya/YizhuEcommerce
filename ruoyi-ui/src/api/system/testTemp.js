import request from '@/utils/request'

// 查询测试列表
export function listTestTemp(query) {
  return request({
    url: '/system/testTemp/list',
    method: 'get',
    params: query
  })
}

// 查询测试详细
export function getTestTemp(id) {
  return request({
    url: '/system/testTemp/' + id,
    method: 'get'
  })
}

// 新增测试
export function addTestTemp(data) {
  return request({
    url: '/system/testTemp',
    method: 'post',
    data: data
  })
}

// 修改测试
export function updateTestTemp(data) {
  return request({
    url: '/system/testTemp',
    method: 'put',
    data: data
  })
}

// 删除测试
export function delTestTemp(id) {
  return request({
    url: '/system/testTemp/' + id,
    method: 'delete'
  })
}
