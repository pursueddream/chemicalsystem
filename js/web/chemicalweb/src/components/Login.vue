<template>
  <div>
    <div>
      <Input v-model="username" placeholder="Enter username" style="width: 300px" />
    </div>
    <div>
      <Input v-model="password" placeholder="Enter password" style="width: 300px" />
    </div>
    <div>
      <div>
        <Button  @click.native="login()">确定</Button>
      </div>
    </div>
  </div>
</template>

<script>
  import  JSEncrypt  from 'jsencrypt'

  export default {
    data () {
      return {
        username : "",
        password : ""
      }
    },

    methods: {
      login(){

        //请求公钥
        this.$http.getPublicKey().then(res => {
          const jsEncrypt = new JSEncrypt();
          jsEncrypt.setPublicKey(res.data);
          //登录
          this.$http.login(this.username, jsEncrypt.encrypt(this.password)).then(res => {
            //登录成功， 跳转至首页
            if (res.data.code === "success"){
              //将登陆信息放入cookie
              $.cookie("Authorization", res.data.token);
              this.$router.push({
                path : "/overview"
              })
            }
          });
        });

      }
    }
  }
</script>
