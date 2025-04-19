<template>
  <el-footer class="footer">
    <div class="footer-content">
      <div class="footer-section">
        <h4>About Us</h4>
        <p>YizhuEcommerce is an e-commerce platform dedicated to delivering a high-quality shopping experience.</p>
      </div>
      <div class="footer-section">
        <h4>Contact</h4>
        <p>Email: jp202121716@qmul.ac.uk</p>
        <p>Address: Queen Mary University of London, UK</p>
      </div>
      <div class="footer-section">
        <h4>Quick Links</h4>
        <el-link type="info" :underline="false" href="/mall">Home</el-link>
        <el-link type="info" :underline="false" href="/mall">Cart</el-link>
        <el-link type="info" :underline="false" href="/mall">Account</el-link>
      </div>
    </div>
    <div class="footer-bottom">
      <div class="copyright">
        © {{ currentYear }} YizhuEcommerce. All rights reserved.
      </div>
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