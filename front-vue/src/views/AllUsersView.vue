<template>
<div class="container-fluid">
  <h1 class="padding-style head-align">View users</h1>
  <div class="row padding-style">
    <div class="col">
      <input type="text" v-model="search" @change="FilterUsers" placeholder="search">
    </div>
    <div class="col">
      <select v-model="filter" @change="FilterUsers">
        <option>All</option>
        <option>Customer</option>
        <option>Trainer</option>
        <option>Manager</option>
        <option>Admin</option>
      </select>
    </div>
    <div class="col">
      <select v-model="sort" @change="FilterUsers">
        <option>Sort</option>
        <option>Name asc</option>
        <option>Name desc</option>
        <option>Surname asc</option>
        <option>Surname desc</option>
        <option>Username asc</option>
        <option>Username desc</option>
        <option>Points asc</option>
        <option>Points desc</option>
      </select>
    </div>
    </div>
  <div class="row row-style row-cols-3 gy-4">
    <div v-for="user in this.FilterUsers()">
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
      users: [],
      search: '',
      filter: 'All',
      filteredUsers: [],
      customers: [],
      sort: 'Sort'
    }
  },
  created() {
    this.GetAllUsers()
  },
  computed:{

  },
  methods:{

    GetAllUsers(){
      this.GetCustomers();
      this.GetManagers();
      this.GetTrainers();
      this.GetAdmins();
      this.filteredUsers = this.users;
    },
    GetCustomers(){
       axios.get('http://localhost:8080/FitnessCenter/rest/customers/')
           .then(
               result => {
                 this.users.push(...result.data)
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
    },
    searchUsers(){
      console.log(this.search);
      return this.users.filter(user => this.search.toLowerCase().split(' ').every(s => user.name.toLowerCase().includes(s))
          || this.search.toLowerCase().split(' ').every(s => user.surname.toLowerCase().includes(s))
          || this.search.toLowerCase().split(' ').every(s => user.username.toLowerCase().includes(s))
      )
    },
    FilterUsers(){
      this.filteredUsers = this.searchUsers();
      if(this.filter !== 'All'){
        this.filteredUsers = [...this.filteredUsers.filter(user =>  user.userRole.toLowerCase().includes(this.filter.toLowerCase()))]
      }
      this.SortUsers();
      return this.filteredUsers
    },
    SortUsers(){
      if(this.sort === 'Name asc'){
        this.filteredUsers.sort((a,b) => (a.name.toLowerCase() < b.name.toLowerCase()) ? -1 : 1)
      }else if(this.sort === 'Name desc'){
        this.filteredUsers.sort((a,b) => (a.name.toLowerCase() < b.name.toLowerCase()) ? 1 : -1)
      }
      else if(this.sort === 'Surname desc'){
        this.filteredUsers.sort((a,b) => (a.surname.toLowerCase() < b.surname.toLowerCase()) ? 1 : -1)
      }
      else if(this.sort === 'Surname asc'){
        this.filteredUsers.sort((a,b) => (a.surname.toLowerCase() < b.surname.toLowerCase()) ? -1 : 1)
      }
      else if(this.sort === 'Username asc'){
        this.filteredUsers.sort((a,b) => (a.username.toLowerCase() < b.username.toLowerCase()) ? -1 : 1)

      } else if(this.sort === 'Username desc'){
        this.filteredUsers.sort((a,b) => (a.username.toLowerCase() < b.username.toLowerCase()) ? 1 : -1)
      }
      else if(this.sort === 'Points asc'){
        this.filteredUsers.sort((a,b) => (a.colletedPoints < b.colletedPoints) ? -1 : 1)

      } else if(this.sort === 'Points desc'){
        this.filteredUsers.sort((a,b) => (a.colletedPoints < b.colletedPoints) ? 1 : -1)
      }
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
  width: 100%;
}
.head-align{
  text-align: center;
}
.margin-style{
  margin-left: 180px;
}
.padding-style{
  margin-bottom: 80px;
  margin-left: 20px;

}
.row-style{
  padding-bottom: 130px;
  margin-bottom: 30px;

}
input,select {
  display: block;
  padding-right: 10px;
  width: 70%;
  border: 1px solid black;
  box-sizing: border-box;
  color: #555;
  font-size: 20px;
  border-radius: 15px;
}
h1{
  font-size: 70px;
  font-weight: bolder;

}

</style>