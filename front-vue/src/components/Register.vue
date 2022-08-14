<template>
  <form @submit.prevent="handleSubmit">
    <h1>Sign up</h1>

    <div class="form-group">
      <label>Name:</label>
      <input type="text" v-model="name">
    </div>

    <div class="form-group">
      <label>Surname:</label>
      <input type="text" v-model="surname">
    </div>

    <div class="form-group">
      <label>Username:</label>
      <input type="text" v-model="username">
    </div>

    <div class="form-group">
      <label>Password:</label>
      <input type="password" v-model="password" autocomplete="off">
    </div>

    <div class="form-group">
      <label>Gender:</label>
      <select v-model="gender" >
        <option>MALE</option>
        <option>FEMALE</option>
      </select>
    </div>

    <div class="form-group">
      <label>Birthday:</label>
      <input type="date" v-model="birthdate">
    </div>

    <button  class="btn btn-primary button-basic">Sign up</button>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "Register",
  data(){
    return{
      name : '',
      surname : '',
      username : '',
      password : '',
      gender : '',
      birthdate : ''
    }
  },
  methods:{
    handleSubmit(){
      const user = {
        name : this.name,
        surname : this.surname,
        username : this.username,
        password : this.password,
        gender : this.gender,
        birthday : this.birthdate

      }
          axios.post('http://localhost:8080/FitnessCenter/rest/customers/Create',user)
              .then(
                  res =>{
                    console.log(res)
                  }
              )
              .catch(
                  error =>
                  {
                    console.log(error)
                  }
              )
            this.$router.push('/login')
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
button:hover {
  background-color: blue;
  box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}

</style>