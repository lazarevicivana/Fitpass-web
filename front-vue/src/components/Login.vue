<template>
  <form  @submit.prevent="handleLogin">
    <h1>Login</h1>
      <label>Username:</label>
      <input type="text" v-model="user.username">
      <label>Password:</label>
      <input type="password" autocomplete="on" v-model="user.password">
    <input  type="submit" class="btn btn-primary button-basic" value="Log in"/>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data(){
    return{
      user : {
        username: '',
        password : ''
      }
    }
  },
  methods :{
    handleLogin(){
       axios.post('http://localhost:8080/FitnessCenter/rest/login/logInStatus',this.user)
              .then(
                  res => {
                    if (res.data) {
                      this.$emit('loggedUser', res.data);
                    }else{
                      this.$notify({
                        title: 'Error while login',
                        text: 'Incorrect password or username!',
                        closeOnClick: true,
                        type: 'error'
                      })
                    }
                  })
            .catch(
                error =>
                {
                  console.log(error)
                })
    }
  },
  emits:['loggedUser']
}
</script>

<style scoped>
form {
  max-width: 600px;
  margin: 80px auto 30px 300px;
  background: #072238;
  text-align: center;
  padding: 40px;
  border-radius: 10px;
  font-size: 18px;
  color: white;
}h1{
   font-weight: bolder;
   font-size: 50px;
 }
label {
  color: white;
  display: inline-block;
  text-align: left;
  margin: 25px 0 15px;
  font-size: 0.6em;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bolder;
  font-size: 18px;
}input,select {
   display: block;
   padding-left: 10px;
   padding-right: 10px;
   margin-left: 70px;
   width: 70%;
   box-sizing: border-box;
   border: none;
   border-bottom: 1px solid #ddd;
   color: #555;
   font-size: 18px;
   border-radius: 15px;

 }
.button-basic{
  color: white;
  margin-top: 40px;
  display: block;
  padding-left: 10px;
  padding-right: 10px;
  margin-left: 55px;
  font-size: 24px;
  width: 50%;
  vertical-align: center;
  box-sizing: border-box;
  border: none;
  position: relative;
  -ms-transform: translateX(-50%);
  transform: translateX(25%);
}
.button-basic{
  margin-top: 40px;
}


</style>