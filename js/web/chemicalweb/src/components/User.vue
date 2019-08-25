<template>
  <div>
    <div>USER</div>
    <div>{{message}}</div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        message : ""
      }
    },
    mounted:function(){
      let params = {
        pageNumber : 1,
        pageSize : 10
      };

      this.$http.get("/user", params).then((res) => {
        console.log(res);
        this.message = res;
      })

    },
    methods: {

    }
  }



</script>
