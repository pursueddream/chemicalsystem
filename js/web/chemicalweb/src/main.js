// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import iView from 'iview'
import 'iview/dist/styles/iview.css'
import HttpService from './utils/HttpService.js'
import jquery from 'jquery'
import cookie from 'jquery.cookie'
import axios from "axios";

window.$ = jquery;

Vue.config.productionTip = false;
Vue.prototype.$http = HttpService;
Vue.use(iView);


/*
* 增加请求拦截器
*/
axios.interceptors.request.use((config) => {
  if(config.url === "/api/v1/login" || config.url === "/api/v1/system/publicKey") {
    config.headers = {
      "withCredentials" : true
    };
  }else {
    config.headers = {
      "Authorization" : $.cookie('Authorization'),
      "withCredentials" : true
    };
  }

  // 预处理请求的信息
  return config
}, (error) => {
  // 预处理请求有异常error时抛出错误
  return Promise.reject(error)
});

/*
* 增加相应拦截器
*/
axios.interceptors.response.use(
  (response) => {
    if (response && response.data) {
      let code = response.data.code;
      if (code === "NOT_PERMISSION" || code === "NOT_LOGIN") {
        //未登录或者没有权限， 直接跳到登录页面
        router.push({
          path : "/"
        });
      }
    }
  return response
  },

  (error) => {


  return Promise.reject(error.response.data)   // 返回接口返回的错误信息
});




router.beforeEach((to, from, next) => {
  let name = to.name;
  if (name === "login"){
    next();
  }
  if ($.cookie("Authorization")) {
    next();
  }else {
    //回到登录页面
    router.push({
      path: "/"
    });
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});


