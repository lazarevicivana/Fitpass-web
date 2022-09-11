<template>
  <div class="div">
    <label class="label"> {{user.name}}, your comment is successfully added!</label>
  </div>
</template>

<script>
export default {
  name: "SuccessCommentView",
  props: {
    user: Object
  }
}

</script>

<style scoped>
.div{
  width: 70%;
  margin: 200px 100px 50px 250px;
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