<template>
  <form @submit.prevent="EditSubmit">
    <h1>User profile</h1>
    <label>Role:</label>
    <input type="text" v-model="user.userRole" disabled="disabled">
    <label>Name:</label>
    <input type="text" v-model="user.name">
    <label>Surname:</label>
    <input type="text" v-model="user.surname">
    <label>Username:</label>
    <input type="text" v-model="user.username" disabled="disabled">
    <label>Password:</label>
    <input type="text" v-model="user.password">
    <label>Gender:</label>
    <input type="text" v-model="user.gender">
    <label>Birthday:</label>
    <input type="date" v-model="user.birthday">
    <input  type="submit" class="btn btn-primary button-basic" value="Submit"/>
  </form>
</template>

<script>

import axios from "axios";

export default {
  name: "ProfileView",
  props:{
    user: Object
  },
  data() {
    return{
      manager:{}
    }
  },
  created() {
    console.log(this.user)
  },
  methods: {
    EditSubmit() {
      if (this.user.userRole === 'MANAGER'){
        console.table(this.user);

      axios.put('http://localhost:8080/FitnessCenter/rest/managers/', this.user)
          .then(
              res => {
                console.log(res.data)
              }
          )
          .catch(
              error => {
                console.log(error)
              }
          )
        }
        else if (this.user.userRole === 'CUSTOMER') {
          axios.put('http://localhost:8080/FitnessCenter/rest/customers/', this.user)
            .then(
                res => {
                  console.log(res.data)
                }
            )
            .catch(
                error => {
                  console.log(error)
                }
            )
        }
        else if (this.user.userRole === 'TRAINER') {
          axios.put('http://localhost:8080/FitnessCenter/rest/trainers/', this.user)
              .then(
                  res => {
                    console.log(res.data)
                  }
              )
              .catch(
                  error => {
                    console.log(error)
                  }
              )
        }
        else {
          axios.put('http://localhost:8080/FitnessCenter/rest/admins/', this.user)
              .then(
                  res => {
                    console.log(res.data)
                  }
              )
              .catch(
                  error => {
                    console.log(error)
                  }
              )
        }
      this.$router.push('/success-profile-edit')
    }

      }
}
</script>

<style scoped>
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
   margin-left: 70px;
   width: 70%;
   box-sizing: border-box;
   border: none;
   border-bottom: 1px solid #ddd;
   color: #555;
   font-size: 20px;
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