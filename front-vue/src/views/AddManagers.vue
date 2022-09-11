<template>
<div class="container">
  <CreateManager :manager="manager"/>
</div>
</template>

<script>
import CreateManager from "@/components/CreateManager";
export default {
  name: "AddManagers",
  components: {CreateManager},
  data() {
    return{
      manager :{
        username: '',
        password : '',
        name : '',
        surname : '',
        birthday : '',
        gender : '',
        userRole : 'MANAGER',
        deleted : false,
        banned : false,
        sportFacilityId: ''
      }
    }
  }
}
</script>

<style scoped>

</style>