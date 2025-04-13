<template>
  <el-footer class="footer">
    <div class="footer-content">
      <div class="footer-section">
        <h4>关于我们</h4>
        <p>YizhuEcommerce是一个专注于提供优质购物体验的电商平台。</p>
      </div>
      <div class="footer-section">
        <h4>联系方式</h4>
        <p>邮箱：jp202121716@qmul.ac.uk</p>
        <p>地址：英国伦敦玛丽女王大学</p>
      </div>
      <div class="footer-section">
        <h4>快速链接</h4>
        <el-link type="info" :underline="false" href="/mall">首页</el-link>
        <el-link type="info" :underline="false" href="/mall">购物车</el-link>
        <el-link type="info" :underline="false" href="/mall">个人中心</el-link>
      </div>
    </div>
    <div class="footer-bottom">
      <div class="copyright">© {{ currentYear }} YizhuEcommerce. All rights reserved.</div>
    </div>
  </el-footer>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const currentYear = ref(new Date().getFullYear())
</script>

<style lang='scss' scoped>
.footer {
  background-color: var(--el-color-info-light-9);
  padding: 40px 0 20px;
  margin-top: 40px;
  height: 260px;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 30px;
  padding: 0 20px;
}

.footer-section {
  text-align: left;
}

.footer-section h4 {
  color: var(--el-color-info-dark-2);
  margin-bottom: 15px;
  font-size: 16px;
  font-weight: 600;
}

.footer-section p {
  color: var(--el-color-info);
  margin: 8px 0;
  font-size: 14px;
  line-height: 1.6;
}

.footer-section ::v-deep(.el-link) {
  display: block;
  margin: 8px 0;
  font-size: 14px;
}

.footer-bottom {
  margin-top: 30px;
  padding-top: 20px;
  border-top: 1px solid var(--el-border-color-lighter);
  text-align: center;
}

.copyright {
  color: var(--el-color-info);
  font-size: 14px;
}

@media screen and (max-width: 768px) {
  .footer-content {
    grid-template-columns: 1fr;
    gap: 20px;
  }

  .footer-section {
    text-align: center;
  }

  .footer {
    padding: 30px 0 15px;
  }
}
</style>