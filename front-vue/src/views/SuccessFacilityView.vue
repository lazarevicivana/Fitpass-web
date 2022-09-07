<template>
  <div class="div">
    <label class="label"> Facility is successfully created!</label>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "SuccessFacilityView",
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
  margin: 80px auto;
  background: #072238;
  text-align: center;
  padding: 40px;
  border-radius: 10px;
  color: white;
}

.label{
  font-size: 22px;
}

</style>