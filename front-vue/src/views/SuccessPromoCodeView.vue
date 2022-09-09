<template>
  <div class="div">
    <label class="label"> Promo code is successfully added!</label>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "SuccessPromoCodeView",
  props:{

  },
  data(){
  },
  created(){

  },
  methods:{
    getData(){  }

  }
}
</script>

<style scoped>

.div{
  max-width: 500px;
  margin: 100px 150px 150px 420px;
  alignment: center;
  background: #072238;
  text-align: center;
  padding: 40px;
  border-radius: 10px;
  color: white;
}

.label{
  font-size: 28px;
}


</style>