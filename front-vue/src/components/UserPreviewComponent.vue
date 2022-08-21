<template>
<div class="container-sm">
  <table>
    <tr>
      <td>
        <label>Name:</label>
        {{user.name}}
      </td>
    </tr>
    <tr>
      <td>
        <label>Surname:</label>
        {{user.surname}}
      </td>
    </tr>
   <tr>
     <td>
       <label>Role:</label>
       {{user.userRole}}
     </td>
   </tr>
  </table>


</div>
</template>

<script>

export default {
  name: "UserPreviewComponent",
  props: {user: Object},
  created(){
      console.log(this.user.name);
  }
}

</script>

<style scoped>

</style>