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
                  res =>{
                      if(res.data) {
                        const loggIn = this.user;
                        console.log(this.user.username);
                        this.$emit('loggedUser',res.data);
                        //console.log(res.data);
                      }
              }
          )
          .catch(
              error =>
              {
                console.log(error)
              }
          )

      /*localStorage.setItem('token', user.data.token)*/
    }
  },
  emits:['loggedUser']
}
</script>

<style scoped>
.color-overlay {
 position: absolute;
  height: 40%;
  width: 40%;
  mix-blend-mode: normal;
}
.masthed{
  min-height: 100vh;
  position: relative;

}
.center{
  font-family: 'Roboto',Tahoma,Geneva,Verdana,SansSerif,'Gill Sans MT',Calibri, 'Trebuchet MS', sans-serif, 'Arial Narrow', Arial, sans-serif, Courier, monospace;
  text-align: center;
  padding: 0 0 20px 0;
  color: white;

}
form {
  max-width: 500px;
  margin: 30px auto;
  background: #072238;
  text-align: center;
  padding: 40px;
  border-radius: 10px;
  font-size: 15px;
  color: white;
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
  font-size: 15px;
}input,select {
   display: block;
   padding-left: 10px;
   padding-right: 10px;
   margin-left: 48px;
   width: 80%;
   box-sizing: border-box;
   border: none;
   border-bottom: 1px solid #ddd;
   color: #555;
   font-size: 20px;
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