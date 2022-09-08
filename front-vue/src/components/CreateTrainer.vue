<template>
  <form @submit.prevent="TrainerSubmit">
    <h1>Add trainer</h1>
    <label>Name:</label>
    <input type="text" v-model="trainer.name" required>
    <label>Surname:</label>
    <input type="text" v-model="trainer.surname" required>
    <label>Username:</label>
    <input type="text" v-model="trainer.username" required>
    <label>Password:</label>
    <input type="password" v-model="trainer.password" required autocomplete="off">
    <label>Gender:</label>
    <select v-model="trainer.gender" required>
      <option>Male</option>
      <option>Female</option>
    </select>
    <label>Birthday:</label>
    <input type="date" v-model="trainer.birthday" required>
    <input  type="submit" class="btn btn-primary button-basic" value="Add"/>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "CreateTrainer",
  data(){
    return{
      trainer:{
        username: '',
        password : '',
        name : '',
        surname : '',
        birthday : '',
        gender : '',
        userRole : ''
      },
      trainers: [],
      name: true
    }
  },
  created() {
    this.getTrainers()
  },
  methods: {
    TrainerSubmit(){
      this.name = true
      this.trainers.forEach(t =>{
        if(t.username === this.trainer.username){
          this.name = false
          if (this.name === false) {
            this.$notify({
              title: 'Error while creating trainer',
              type: 'error',
              text: "A trainer with that username already exists in database!",
              closeOnClick: true
            })
            return;
          }
        }})
      if(this.name){
      axios.post('http://localhost:8080/FitnessCenter/rest/trainers/create',this.trainer)
          .then(
              result => {
                console.log(result.data)
              }
          )
          .catch(
              error => {
                console.log(error)
              }
          )}
    },
    getTrainers(){
      axios.get('http://localhost:8080/FitnessCenter/rest/trainers/')
          .then(
              result => {
                this.trainers = result.data
              }
          )
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