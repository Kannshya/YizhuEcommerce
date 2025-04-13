<!-- @/layout/mallLayout.vue -->
<template>
  <div>
    <mall-header></mall-header>
    <div class="router-view">
      <router-view /> <!-- 页面内容渲染在这里 -->
    </div>
    <mall-footer></mall-footer>
  </div>
</template>

<script>
import MallHeader from './mallComponents/mallHeader.vue'
import MallFooter from './mallComponents/mallFooter.vue'

export default {
  name: "frontLayout",
  components: {
    MallHeader,
    MallFooter
  }
}
</script>

<style lang='scss' scoped>
.router-view{
  min-height: calc(100vh - 320px);
  padding-top: 60px;
}
</style>