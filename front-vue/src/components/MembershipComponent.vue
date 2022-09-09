<template>
  <div class="container-sm">
    <table class="justify-content-end">
      <tr>
        <td class="td-style">
          <label>Type: </label>
        </td>
        <td class="td-style">
          <label>  {{membership.type}}</label>
        </td>
      </tr>
      <tr>
        <td class="td-style">
          <label>Trainings: </label>
        </td>
        <td class="td-style">
          <label>{{membership.numberOfTrainings}}</label>
        </td>
      </tr>
      <tr>
        <td class="td-style">
          <label>Description: </label>
        </td>
        <td class="td-style">
          <label>{{membership.description}}</label>
        </td>
      </tr>
    </table>


  </div>

</template>

<script>
export default {
  name: "MembershipComponent",
  props:{
    membership : Object
  }
}
</script>

<style scoped>
.td-style{
  text-align: left;
  font-size: 18px;
  font-weight: bold;
}
</style>