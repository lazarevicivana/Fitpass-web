<template>
<div class="container-fluid margin-style">
  <h1 class="padding-style">View users</h1>
  <div class="row row-style gy-4 row-cols-3 align-items-center" >
    <div v-for="user in users">
      <div class="col-lg-8 colorDiv">
        <user-preview-component :user="user"></user-preview-component>
      </div>
    </div>

  </div>

</div>
</template>

<script>
import UserPreviewComponent from "@/components/UserPreviewComponent";
import axios from "axios";
export default {
  name: "AllUsersView",
  components: {UserPreviewComponent},
  data(){
    return{
      users: []
    }
  },
  created() {
    this.GetAllUsers()
   // axios.get()
  },
  methods:{
    GetAllUsers(){
      this.GetCustomers();
      this.GetManagers();
      this.GetTrainers();
      this.GetAdmins();
    },
    GetCustomers(){
       axios.get('http://localhost:8080/FitnessCenter/rest/customers/')
           .then(
               result => {
                 this.users.push(...result.data)
                 //this.users.push(result.data)
                 console.log(this.users)
               }
           )
    },
    GetManagers(){
      axios.get('http://localhost:8080/FitnessCenter/rest/managers/GetAll')
          .then(
              result => {
                this.users.push(...result.data)
              }
          )
    },
    GetTrainers(){
      axios.get('http://localhost:8080/FitnessCenter/rest/trainers/')
          .then(
              result => {
                this.users.push(...result.data)
              }
          )
    },
    GetAdmins(){
      axios.get('http://localhost:8080/FitnessCenter/rest/admins')
          .then(
              result => {
                this.users.push(...result.data)
              }
          )
    }
  }
}

</script>

<style scoped>
.colorDiv{
  background: #072238;
  border-radius: 25px;
  border: 2px solid;
  padding: 20px;
  color: white;
}
.margin-style{
  margin-left: 180px;
}
.padding-style{
  margin-bottom: 80px;
}
.row-style{
  padding-bottom: 100px;
  margin-bottom: 30px;
}

</style>