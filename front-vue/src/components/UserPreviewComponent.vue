<template>
<div class="container-sm">
  <table class="justify-content-end">
    <tr>
      <td class="td-style">
        <label>Name:</label>
      </td>
      <td class="td-style">
        <label>  {{user.name}}</label>
      </td>
    </tr>
    <tr>
      <td class="td-style">
        <label>Surname:</label>
      </td>
      <td class="td-style">
        <label>{{user.surname}}</label>
      </td>
    </tr>
    <tr>
      <td class="td-style">
        <label>Username:</label>
      </td>
      <td class="td-style">
        <label>{{user.username}}</label>
      </td>
    </tr>
   <tr>
     <td class="td-style">
       <label>Role:</label>
     </td>
     <td class="td-style">
       <label>{{user.userRole}}</label>
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
  }
}

</script>
<style>
.td-style{
  text-align: left;
  font-size: 18px;
  font-weight: bold;
}

</style>