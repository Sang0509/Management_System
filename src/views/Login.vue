<template>
  <div id="app">
    <div style="flex: 3; font-size: 30px ;text-align: center">
      <span><img src="../assets/logo.png" alt="" style="width: 40px; top: 10px; right: 10px"><b style="text-align: center">社员管理系统</b></span>
    </div>
  <div class="login-wrapper" style="background-color: #eeeeee">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px" class="login-form">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" placeholder="请输入密码" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="form.remember">记住密码</el-checkbox>
        <el-button type="text" class="forget-password" @click="handleForgetPassword">忘记密码？</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleLogin" :loading="loading">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        username: '',
        password: '',
        bool:'',
        form: {},
        remember: false
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      },
      loading: false
    }
  },
  methods: {
    handleLogin() {
      this.$refs.form.validate(valid => {
        if (valid) {
            fetch("http://localhost:9090/login?username="+this.form.username+"&password=" +this.form.password)
                .then(res => res.json())
                .then(res => {
                  console.log(this.form.username + "AAAAAAAA"+ this.form.password)
                  console.log(res)
                  this.bool = res
                  if (this.bool == 1){
                    this.$router.push('/home')
                    history.pushState(null, null, document.URL);
                    window.addEventListener("popstate",function(e) {
                      history.pushState(null, null, document.URL);
                    }, false);
                  }else {
                    window.alert("ログイン失敗、再度ご確認をお願いいたします。")
                  }
                })
        }
      })
    },
    handleForgetPassword() {
      // 处理忘记密码逻辑
    }
  }
}
</script>

<style>
.login-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-form {
  width: 400px;
  padding: 30px;
  border: 1px solid #ccc;
  border-radius: 10px;
}

.forget-password {
  float: right;
}
</style>