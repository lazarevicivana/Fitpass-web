<template>
<div class="div">
  <label class="label"> {{user.name}}, your profile is successfully updated!</label>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "SuccessEditProfileView",
  props:{
    user: Object
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
  width: 70%;
  margin: 200px 100px 50px 350px;
  background: #072238;
  text-align: center;
  padding: 40px;
  border-radius: 15px;
  color: white;
}
label{
  font-size: 40px;
  margin: 30px;
}
</style>