import axios from 'axios'
import Vue from 'vue'

export default {
  getPublicKey(){
    const config= {
      url : "/system/publicKey",
      method : "get"
    };
    return axios.request(config);
  },

  login(username, password){
    const config= {
      url : "/login",
      params : {
        username : username,
        password : password
      },
      method : "post",
    };
    return axios.request(config);
  },

  get(url, params){
    const config= {
      url : url,
      params : params,
      method : "get",
    };
    return axios.request(config);
  },

  post(url, data){
    const config= {
      url : url,
      data : data,
      method : "post"
    };
    return axios.request(config);
  },

  put(url, data){
    const config= {
      url : url,
      data : data,
      method : "put"
    };
    return axios.request(config);
  },

  delete(url, data){
    const config= {
      url : url,
      data : data,
      method : "delete"
    };
    return axios.request(config);
  }
}





