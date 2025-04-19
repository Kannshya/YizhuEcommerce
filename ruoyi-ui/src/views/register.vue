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
          placeholder="Account"
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
          placeholder="Password"
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
            placeholder="Confirm password"
            @keyup.enter="handleRegister"
        >
          <template #prefix><svg-icon icon-class="password" class="el-input__icon input-icon" /></template>
        </el-input>
      </el-form-item>
      <el-form-item label="User type" prop="userType">
        <el-radio-group v-model="registerForm.userType">
          <el-radio label="01">Consumer</el-radio>
          <el-radio label="02">Merchant</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="email">
        <el-input
          v-model="registerForm.email"
          type="text"
          size="large"
          auto-complete="off"
          placeholder="Email adress"
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
          {{ emailCodeTimer > 0 ? `try ${emailCodeTimer} seconds later` : 'send code' }}
        </el-button>
      </el-form-item>
      <el-form-item prop="emailCode">
        <el-input
          v-model="registerForm.emailCode"
          type="text"
          size="large"
          auto-complete="off"
          placeholder="Verification code"
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
          placeholder="Phonenumber"
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
        <el-button type="primary">Upload Business License</el-button>
          <template #tip>
            <div class="el-upload__tip">Only JPG/PNG files can be uploaded, and the size must not exceed 2MB.</div>
          </template>
        </el-upload>
      </el-form-item>

      <el-form-item prop="code" v-if="captchaEnabled">
        <el-input
          size="large" 
          v-model="registerForm.code"
          auto-complete="off"
          placeholder="code"
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
          <span v-if="!loading">Register</span>
          <span v-else>Registering...</span>
        </el-button>
        <div style="float: right;">
          <router-link class="link-type" :to="'/login'">Log in</router-link>
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
    { required: true, trigger: "blur", message: "Please enter your username" },
    { min: 2, max: 20, message: "Username length must be between 2 and 20 characters", trigger: "blur" }
  ],
  password: [
    { required: true, trigger: "blur", message: "Please enter your password" },
    { min: 5, max: 20, message: "Password length must be between 5 and 20 characters", trigger: "blur" },
    { pattern: /^[^<>"'|\\]+$/, message: "Cannot contain illegal characters: < > \" ' \\ |", trigger: "blur" }
  ],
  confirmPassword: [
    { required: true, trigger: "blur", message: "Please confirm your password" },
    { required: true, validator: equalToPassword, trigger: "blur" }
  ],
  code: [{ required: true, trigger: "change", message: "Please enter the CAPTCHA code" }],
  email: [{ required: true, trigger: "blur", message: "Please enter your email address" }],
  emailCode: [{ required: true, trigger: "blur", message: "Please enter the email verification code" }]
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
    ElMessage.error('Only JPG/PNG images are allowed!');
    return false;
  }
  if (!isLt2M) {
    ElMessage.error('Image size must not exceed 2MB!');
    return false;
  }
  return true;
}

// Handle file upload success
function handleUploadSuccess(response) {
  if (response.code === 200) {
    registerForm.value.businessLicense = response.fileName;
    console.log(registerForm, registerForm.value);
    ElMessage.success('Business license uploaded successfully');
  } else {
    ElMessage.error('Failed to upload business license');
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
  background-color: #9aabaa;
  // background-image: url("../assets/images/login-background.jpg");
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
