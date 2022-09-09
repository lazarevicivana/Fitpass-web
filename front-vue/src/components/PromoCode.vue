<template>
  <table class="justify-content-end">
    <tr class="align-items-center">
      <td><label>Code:</label></td>
      <td><label style="margin-left: 30px">{{promoCode.code}}</label></td>
    </tr>
    <tr>
      <td><label >Usage:</label></td>
      <td> <label style="margin-left: 30px"> {{promoCode.numberOfUsage}}</label></td>
    </tr>
  </table>

</template>

<script>
export default {
  name: "PromoCode",
  props:{
    promoCode: Object
  }
}
</script>

<style scoped>
table{
  font-size: 24px;

}
</style>