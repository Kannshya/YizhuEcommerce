<template>
  <div class="register">
    <el-form ref="registerRef" :model="registerForm" :rules="registerRules" class="register-form">
      <h3 class="title">{{ title }}</h3>
      <el-form-item prop="username">
        <el-input 
          v-model="registerForm.username" 
          type="text" 
          size="large" 
          auto-complete="off" 
          placeholder="账号"
        >
          <template #prefix><svg-icon icon-class="user" class="el-input__icon input-icon" /></template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="registerForm.password"
          type="password"
          size="large" 
          auto-complete="off"
          placeholder="密码"
          @keyup.enter="handleRegister"
        >
          <template #prefix><svg-icon icon-class="password" class="el-input__icon input-icon" /></template>
        </el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword">
        <el-input
            v-model="registerForm.confirmPassword"
            type="password"
            size="large"
            auto-complete="off"
            placeholder="确认密码"
            @keyup.enter="handleRegister"
        >
          <template #prefix><svg-icon icon-class="password" class="el-input__icon input-icon" /></template>
        </el-input>
      </el-form-item>
      <el-form-item label="用户类型" prop="userType">
        <el-radio-group v-model="registerForm.userType">
          <el-radio label="01">用户</el-radio>
          <el-radio label="02">商家</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="email">
        <el-input
          v-model="registerForm.email"
          type="text"
          size="large"
          auto-complete="off"
          placeholder="邮箱地址"
          style="width: 70%"
        >
          <template #prefix><svg-icon icon-class="email" class="el-input__icon input-icon" /></template>
        </el-input>
        <el-button
          type="primary"
          :disabled="emailCodeTimer > 0"
          @click="toSendEmailCode"
          style="width: 28%; margin-left: 2%"
        >
          {{ emailCodeTimer > 0 ? `${emailCodeTimer}秒后重试` : '发送验证码' }}
        </el-button>
      </el-form-item>
      <el-form-item prop="emailCode">
        <el-input
          v-model="registerForm.emailCode"
          type="text"
          size="large"
          auto-complete="off"
          placeholder="邮箱验证码"
        >
          <template #prefix><svg-icon icon-class="validCode" class="el-input__icon input-icon" /></template>
        </el-input>
      </el-form-item>
      <el-form-item prop="phonenumber">
        <el-input
          v-model="registerForm.phonenumber"
          type="text"
          size="large"
          auto-complete="off"
          placeholder="手机号码"
        >
          <template #prefix><svg-icon icon-class="phone" class="el-input__icon input-icon" /></template>
        </el-input>
      </el-form-item>
      <el-form-item prop="businessLicense" v-if="registerForm.userType === '02'">
        <el-upload
          class="upload-demo"
          action="/dev-api/common/upload"
          :on-success="handleUploadSuccess"
          :before-upload="beforeUpload"
          :limit="1"
        >
          <el-button type="primary">上传营业执照</el-button>
          <template #tip>
            <div class="el-upload__tip">只能上传jpg/png文件，且不超过2mb</div>
          </template>
        </el-upload>
      </el-form-item>

      <el-form-item prop="code" v-if="captchaEnabled">
        <el-input
          size="large" 
          v-model="registerForm.code"
          auto-complete="off"
          placeholder="验证码"
          style="width: 63%"
          @keyup.enter="handleRegister"
        >
          <template #prefix><svg-icon icon-class="validCode" class="el-input__icon input-icon" /></template>
        </el-input>
        <div class="register-code">
          <img :src="codeUrl" @click="getCode" class="register-code-img"/>
        </div>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="large" 
          type="primary"
          style="width:100%;"
          @click.prevent="handleRegister"
        >
          <span v-if="!loading">注 册</span>
          <span v-else>注 册 中...</span>
        </el-button>
        <div style="float: right;">
          <router-link class="link-type" :to="'/login'">使用已有账户登录</router-link>
        </div>
      </el-form-item>
    </el-form>
    <!--  底部  -->
    <div class="el-register-footer">
      <span>© 2025 YizhuEcommerce. All rights reserved.</span>
    </div>
  </div>
</template>

<script setup>
import { ElMessageBox, ElMessage } from "element-plus";
import { getCodeImg, register, sendEmailCode } from "@/api/login";

const title = import.meta.env.VITE_APP_TITLE;
const router = useRouter();
const { proxy } = getCurrentInstance();

const registerForm = ref({
  username: "",
  password: "",
  confirmPassword: "",
  code: "",
  uuid: "",
  userType: "01",
  email: "",
  emailCode: "",
  phonenumber: "",
  businessLicense: ""
});

const equalToPassword = (rule, value, callback) => {
  if (registerForm.value.password !== value) {
    callback(new Error("两次输入的密码不一致"));
  } else {
    callback();
  }
};

const emailCodeTimer = ref(0);

function startEmailCodeTimer() {
  emailCodeTimer.value = 60;
  const timer = setInterval(() => {
    emailCodeTimer.value--;
    if (emailCodeTimer.value <= 0) {
      clearInterval(timer);
    }
  }, 1000);
}

function toSendEmailCode() {
  if (!registerForm.value.email) {
    ElMessage.error('请先输入邮箱地址');
    return;
  }
  sendEmailCode(registerForm.value).then(res => {
    if (res.code === 200) {
      ElMessage.success('验证码已发送，请查收');
      startEmailCodeTimer();
    } else {
      ElMessage.error(res.msg);
    }
  });
}

const registerRules = {
  username: [
    { required: true, trigger: "blur", message: "请输入您的账号" },
    { min: 2, max: 20, message: "用户账号长度必须介于 2 和 20 之间", trigger: "blur" }
  ],
  password: [
    { required: true, trigger: "blur", message: "请输入您的密码" },
    { min: 5, max: 20, message: "用户密码长度必须介于 5 和 20 之间", trigger: "blur" },
    { pattern: /^[^<>"'|\\]+$/, message: "不能包含非法字符：< > \" ' \\\ |", trigger: "blur" }
  ],
  confirmPassword: [
    { required: true, trigger: "blur", message: "请再次输入您的密码" },
    { required: true, validator: equalToPassword, trigger: "blur" }
  ],
  code: [{ required: true, trigger: "change", message: "请输入验证码" }],
  email: [{ required: true, trigger: "blur", message: "请输入邮箱地址" }],
  emailCode: [{ required: true, trigger: "blur", message: "请输入邮箱验证码" }]
};

const codeUrl = ref("");
const loading = ref(false);
const captchaEnabled = ref(true);

function handleRegister() {
  proxy.$refs.registerRef.validate(valid => {
    if (valid) {
      loading.value = true;
      register(registerForm.value).then(res => {
        const username = registerForm.value.username;
        ElMessageBox.alert("<font color='red'>恭喜你，您的账号 " + username + " 注册成功！</font>", "系统提示", {
          dangerouslyUseHTMLString: true,
          type: "success",
        }).then(() => {
          router.push("/login");
        }).catch(() => {});
      }).catch(() => {
        loading.value = false;
        if (captchaEnabled) {
          getCode();
        }
      });
    }
  });
}

function getCode() {
  getCodeImg().then(res => {
    captchaEnabled.value = res.captchaEnabled === undefined ? true : res.captchaEnabled;
    if (captchaEnabled.value) {
      codeUrl.value = "data:image/gif;base64," + res.img;
      registerForm.value.uuid = res.uuid;
    }
  });
}


// 文件上传前的验证
function beforeUpload(file) {
  const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
  const isLt2M = file.size / 1024 / 1024 < 2;

  if (!isJpgOrPng) {
    ElMessage.error('只能上传JPG/PNG格式的图片!');
    return false;
  }
  if (!isLt2M) {
    ElMessage.error('图片大小不能超过2MB!');
    return false;
  }
  return true;
}

// 文件上传成功的处理
function handleUploadSuccess(response) {
  if (response.code === 200) {
    registerForm.value.businessLicense = response.fileName;
    console.log(registerForm, registerForm.value)
    ElMessage.success('营业执照上传成功');
  } else {
    ElMessage.error('营业执照上传失败');
  }
}

getCode();
</script>

<style lang='scss' scoped>
.register {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
}
.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.register-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 40px;
    input {
      height: 40px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 0px;
  }
}
.register-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.register-code {
  width: 33%;
  height: 40px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-register-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.register-code-img {
  height: 40px;
  padding-left: 12px;
}
</style>
