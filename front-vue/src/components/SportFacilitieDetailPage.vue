<template>
<div class="sportFacility">
  <h3>{{name}}</h3>
</div>
</template>

<script>
export default {
  name: "SportFacilitieDetailPage",
  props:{
    sportFacility : Object
  },
  data(){
    return{
      name : ''
   /* sportFacility:{
      name : '',
      location : '',
      isWorking : false,
      type : ''*/


    }
  }
,created() {
    this.name = this.$route.params.data;
  }
}
</script>

<style scoped>

</style>