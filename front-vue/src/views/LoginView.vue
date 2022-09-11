<template>
<Login @loggedUser="emitLoggedUser" />
</template>

<script>
import Login from "@/components/Login";
import axios from "axios";
export default {
  name: "LoginView",

  data(){
    return{
      user : {
        username: '',
        password: ''
      },
      logged:false
    }
  },
  created()
      {
      },
    methods: {
    emitLoggedUser(user) {
      this.logged = user
      this.$emit('loggedUser', this.logged);
      this.$router.push('/')
    }
    },
  components: {Login},
  emits: ['loggedUser'],
}
</script>

<style scoped>

</style>