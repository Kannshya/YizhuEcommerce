<template>
  <div class="mall-container">
    <el-carousel :interval="3000" height="400px" class="mall-carousel">
      <el-carousel-item v-for="(item, index) in carouselImages" :key="index">
        <img :src="item.url" :alt="item.title" class="carousel-image">
      </el-carousel-item>
    </el-carousel>

    <div class="product-grid">
      <div v-for="(product, index) in products" :key="index" class="product-card">
        <img :src="product.url" :alt="product.title" class="product-image">
        <h3 class="product-title">{{ product.title }}</h3>
      </div>
    </div>
  </div>
</template>

<script setup>
const carouselImages = [
  {
    url: new URL('@/assets/images/carousel0.jpg', import.meta.url).href,
    title: 'WenJie M9'
  },
  {
    url: new URL('@/assets/images/carousel1.jpg', import.meta.url).href,
    title: 'HUAWEI Pura X'
  },
  {
    url: new URL('@/assets/images/carousel2.jpg', import.meta.url).href,
    title: 'HUAWEI Mate70 Pro+'
  }
];

const products = [
  {
    url: new URL('@/assets/images/carousel0.jpg', import.meta.url).href,
    title: 'WenJie M9'
  },
  {
    url: new URL('@/assets/images/carousel1.jpg', import.meta.url).href,
    title: 'HUAWEI Pura X'
  },
  {
    url: new URL('@/assets/images/carousel2.jpg', import.meta.url).href,
    title: 'HUAWEI Mate70 Pro+'
  },
  {
    url: new URL('@/assets/images/carousel1.jpg', import.meta.url).href,
    title: 'HUAWEI Pura X'
  },
  {
    url: new URL('@/assets/images/carousel2.jpg', import.meta.url).href,
    title: 'HUAWEI Mate70 Pro+'
  },
  {
    url: new URL('@/assets/images/carousel0.jpg', import.meta.url).href,
    title: '问界M9'
  },
];
</script>

<style scoped>
.mall-container {
  width: 100%;
  padding: 20px;
}

.mall-carousel {
  width: 100%;
  border-radius: 8px;
  overflow: hidden;
  margin-bottom: 30px;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.product-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  padding: 20px 0;
  max-width: 1200px;
  margin: 0 auto;
}

.product-card {
  background: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

.product-card:hover {
  transform: translateY(-5px);
}

.product-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.product-title {
  padding: 15px;
  margin: 0;
  font-size: 16px;
  color: #333;
  text-align: center;
}
</style>