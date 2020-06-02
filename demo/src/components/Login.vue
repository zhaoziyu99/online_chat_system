<template>
  <el-container class='login'>
    <el-form ref='AccountFrom' :model='account' :rules='rules' lable-position='left' lable-width='0px'
             class='demo-ruleForm login-container'>
      <h3 class="title">LOGIN</h3>
      <el-form-item  prop="username" >
        <el-input  type="text" v-model="account.username" auto-complete="off" placeholder="账号">
        </el-input>
      </el-form-item>
      <el-form-item prop="pwd" >
        <el-input type="password" v-model="account.pwd" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width:100%" @click.native.prevent='handleLogin' :loading='logining'>登录
        </el-button>
      </el-form-item>
      <div style="float: bottom">
        <el-link  type="danger"  :underline="false" href="Register.vue">立即注册</el-link>
      </div>
      </el-form>
  </el-container>

</template>

<script>
  //引入api.js  好调用里面的接口
  import {requestLogin} from '../api/api';

  export default {
    name: 'login',
    data() {
      return {
        logining: false,
        account: {
          username: '',
          pwd: ''
        },
        rules: {
          username: [{
            required: true,
            message: '请输入账号',
            trigger: 'blur'
          }],
          pwd: [{
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }]
        },
        checked: true
      }
    },
    methods: {

      handleLogin() {
        this.$refs.AccountFrom.validate((valid) => {

          if (valid) {
            //验证通过 可以提交
            this.logining = true;
            //将提交的数据进行封装
            var loginParams = {cUsername: this.account.username, cPwd: this.account.pwd};
            this.logining = false;
            sessionStorage.setItem('access-token', 0);
            //用vue路由跳转到后台主界面
            this.$router.push({path: '/home'});
            //调用函数  传递参数 获取结果
            requestLogin(loginParams).then(data => {

              this.logining = false;

              if (data.code == '200') {
                //登录成功
                sessionStorage.setItem('access-token', data.token);
                //用vue路由跳转到后台主界面
                this.$router.push({path: '/home'});
              } else {
                this.$message({
                  message: data.msg,
                  type: 'error'
                });
              }
            })

          } else {
            console.log('error submit');
            return false;
          }
        });
      }
    }
  }
</script>

<style>
  .el-input__inner {
    height: 40px;
    font-size: 20px;
    color: white;
    box-shadow: none;
    border: 1px solid #e9e9e9;
    background-color: rgba(255, 255, 255, 0);
  }
  .el-input__inner:hover {
    border-color: #e9e9e9;
  }

  .el-input__inner:focus {
    border-color: #42a5f5;
    box-shadow: none;
    transition-duration: .5s;
  }

  .el-input__inner::-webkit-input-placeholder {
    line-height: 20px;
    color:#3c3f41;
  }

  .el-input__inner, .el-checkbox__inner, .el-textarea__inner, .el-button {
    border-radius: 0;
  }

  .el-button{
    height: 40px;
    font-size: 20px;
    line-height: 0px;
    color:white;
    box-shadow: none;
    background-color: blue;
    border-color: red;
  }
  .el-button:hover{
    background-color: red;
    border-color: red;
  }
  .el-button:focus{
    background-color: #EF9E6F;
    border-color: #EF9E6F;
  }
  body {
    background-image:url("https://www.battlenet.com.cn/login/static/images/login/background/login-background-1280.4JLnG.jpg") ;
    background-repeat: no-repeat;

  }
  .login{
    width: 240px;
    margin: 0 auto;

  }
  .login-container {
    width: 200px;
    margin: 0 auto;
  }


</style>
