<template>
  <form @submit.prevent="createManagerAndFacility">
  <label>Name:</label>
  <input type="text" v-model="manager.name">
  <label>Surname:</label>
  <input type="text" v-model="manager.surname">
  <label>Username:</label>
  <input type="text" v-model="manager.username">
  <label>Password:</label>
  <input type="password" v-model="manager.password" autocomplete="off">
  <label>Gender:</label>
  <select v-model="manager.gender" >
    <option>Male</option>
    <option>Female</option>
  </select>
  <label>Birthday:</label>
  <input type="date" v-model="manager.birthday">
    <input type="submit" class="btn btn-primary button-basic" value="Create"/>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "AddManagerForFacility",
  props:{
    facility: Object,
    id: Number
  },
  data() {
    return {
      manager: {
        username: '',
        password: '',
        name: '',
        surname: '',
        birthday: '',
        gender: '',
        userRole: '',
        /* deleted : false,
         banned : false,*/
        sportFacilityId: ''
      }
    }
  },
  methods:{
    createManagerAndFacility(){
      axios.post('http://localhost:8080/FitnessCenter/rest/facilities/',this.facility)
          .then(
              response => {
                console.log(this.facility)
                this.manager.sportFacilityId = String(this.id)
                axios.post('http://localhost:8080/FitnessCenter/rest/managers/create',this.manager)
                      .then(
                          res =>{
                            console.log(res.data)
                          }
                      )
              }
          );
    }
  }
}
</script>

<style scoped>

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