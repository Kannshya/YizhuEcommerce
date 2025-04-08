<template>
  <el-header class="header">
    <div class="logo" @click="goHome">YizhuEcommerce</div>
    <div class="nav-menu">
      <el-menu mode="horizontal" :router="true" class="nav-list" :ellipsis="false" :background-color="'var(--el-color-primary-dark-2)'" text-color="#fff" active-text-color="#ffd04b">
        <el-menu-item index="/">
          <template #title>
            <el-icon><House /></el-icon>
            <span>首页</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/cart">
          <template #title>
            <el-icon><ShoppingCart /></el-icon>
            <span>购物车</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/profile">
          <template #title>
            <el-icon><User /></el-icon>
            <span>个人中心</span>
          </template>
        </el-menu-item>
      </el-menu>
    </div>
    <div class="search-box">
      <el-input
        v-model="searchQuery"
        placeholder="搜索商品..."
        class="search-input"
        :prefix-icon="Search"
        @keyup.enter="handleSearch"
        clearable
      />
      <el-button
        type="primary"
        class="search-button"
        :icon="Search"
        @click="handleSearch"
      >搜索</el-button>
    </div>
    <div class="login">
      <el-button
        class="login-button"
        @click="handleLogin"
      >登录</el-button>
    </div>
  </el-header>
</template>

<script setup>
import { ref } from 'vue'
import { Search, House, ShoppingBag, ShoppingCart, User } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const searchQuery = ref('')

const handleSearch = () => {
  if (searchQuery.value.trim()) {
    // TODO: 实现搜索逻辑
    console.log('搜索:', searchQuery.value)
  }
}

const handleLogin = () => {
  router.push('/login')
}

const goHome = () => {
  router.push('/')
}
</script>

<style lang='scss' scoped>
.header {
  background-color: #333333;
  padding: 0 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 60px;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  transition: all 0.3s ease;
}

.logo {
  color: var(--el-color-white);
  font-size: 24px;
  font-weight: bold;
  cursor: pointer;
  transition: opacity 0.3s;
}

.logo:hover {
  opacity: 0.8;
}

.nav-menu {
  flex: 1;
  margin: 0 20px;
  
  .nav-list {
    border: none;
    background-color: transparent;
    
    ::v-deep(.el-menu-item) {
      color: #ffffff;
      
      &:hover, &.is-active {
        background-color: #444444;
      }
      
      .el-icon {
        margin-right: 4px;
      }
    }
  }
}

.search-box {
  display: flex;
  align-items: center;
  flex: 0 1 300px;
  margin: 0 20px;
}

.search-input {
  margin-right: -1px;
}

::v-deep(.el-input__wrapper) {
  border-radius: 4px 0 0 4px;
}

.search-button {
  border-radius: 0 4px 4px 0;
  margin-left: -1px;
}

.login-button {
  background: transparent;
  border: 1px solid #ffffff;
  color: #ffffff;
}

.login-button:hover {
  background-color: #ffffff;
  color: #333333;
}

@media screen and (max-width: 1024px) {
  .header {
    padding: 0 20px;
  }

  .nav-menu {
    margin: 0 10px;
  }

  .search-box {
    flex: 0 1 200px;
    margin: 0 10px;
  }

  .logo {
    font-size: 20px;
  }
}

@media screen and (max-width: 768px) {
  .nav-menu {
    ::v-deep(.el-menu-item span) {
      display: none;
    }
  }

  .search-box {
    flex: 0 1 150px;
  }
}
</style>
