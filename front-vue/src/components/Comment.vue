<template>
  <div class="container-sm">
    <div class="row">
      <div class="col">
        <table class="justify-content-end">
          <tr>
            <td class="td-style title">
              <label>  {{comment.text}}</label>
            </td>
          </tr>
          <tr>
            <td class="td-style">
              <label>Grade:</label>
            </td>
            <td class="td-style">
              <label>{{comment.grade}}</label>
            </td>
          </tr>
          <tr v-if="user.userRole === 'ADMIN' || user.userRole === 'MANAGER'">
          <td class="td-style">
            <label>State:</label>
          </td>
          <td class="td-style">
            <label>{{comment.state}}</label>
          </td>
        </tr>

        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Comment",
  props:{
    comment: Object,
    user: Object
  }
}
</script>

<style scoped>
.td-style{
  text-align: left;
  font-size: 18px;
  font-weight: bold;
}
.size{
  width: 200px;
  height: 200px;
}
.title{
  font-size: 40px;
  font-weight: bold;
  margin-bottom: 50px;
  padding-bottom: 10px;
}

</style>