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
          <label>{{trainingsNumber(membership)}}</label>
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
  },
  methods:{
    trainingsNumber(membership){
      if(membership.numberOfTrainings > 30)
      {
        return 'unlimited'
      }else return membership.numberOfTrainings.toString()
    }
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