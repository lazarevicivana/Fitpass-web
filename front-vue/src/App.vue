<template>
  <div class="container-fluid">
    <div class="row">
      <nav @loggedUser="loggedInUser" class="navbar navbar-expand-xxl navbar-light" >
        <ul class="navbar-nav ms-auto text-center">
          <li>
          <router-link v-if="!loggedIn" class="nav-link"  to="/login">Login</router-link>
          </li>
          <li>
            <router-link v-if="!loggedIn" class="nav-link"  to="/register">Sign up</router-link>
          </li>
        </ul>

        <div>
          <button class="button-basic" @click="logOut" v-if="loggedIn" style="float: right">Log out</button>
        </div>
      </nav>
    </div>
  </div>
  <SideBarView :user="user"
                :loggedIn="loggedIn"/>
  <div class="container margin"  >
    <router-view @loggedUser="loggedInUser"  :user="user"/>
  </div>
</template>
<style>
.margin {
  margin-left: 300px;
  max-width: 1220px;

}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #42b983;
}

.button-basic{
background: #2c3e50;
  color: white;
  border-radius: 10px;
  width: 100%;
  height: 100%;
}
.button-basic{
}
</style>
<script>
import SideBarView from "@/components/SideBarView";
import { sidebarWidth } from '@/components/state'
import HeaderView from "@/views/HeaderView";
import axios from "axios";
export default {
  components: {
    SideBarView,
    HeaderView
  },
  data(){
    return{
      user: {
        username: '',
        password : '',
        name : '',
        surname : '',
        birthday : '',
        gender : '',
        userRole : '',
        deleted : false,
        banned : false
      },
      loggedIn : false,
      membership:{}
    }
  },
  created()
  {
  },
  methods :{
    loggedInUser(obj){
     this.loggedIn = obj;
     if (this.loggedIn === true){
       this.GetCurrentUser();
     }
    },
    countCollectedPoints(){
      this.user.colletedPoints = (this.membership.price / 1000) * (this.membership.usedTrainings)
      if(this.membership.usedTrainings < (this.membership.numberOfTrainings / 3)){
        const lostPoints = (this.membership.price / 1000) * 133 * 4
        this.user.colletedPoints = this.user.colletedPoints - lostPoints
      }
      this.updateCollectedPoints()
    },
    updateCollectedPoints(){
      axios.put('http://localhost:8080/FitnessCenter/rest/customers/collected-points/', this.user)
          .then(
            response => {
              this.user = response.data
              console.log(this.user)
                this.checkCollectedPoints()
                this.updateCustomerType(customerType)
            }
        )

    },
    checkCollectedPoints(){
      if(this.user.colletedPoints >= 3000 && this.user.customerType.name === 'BRONZE'){
        const customerType={
          id : this.user.username,
          name: 'SILVER',
          discount: 0.7,
          minPoints : 3000
        }
        console.log(customerType)
        this.updateCustomerType(customerType)
      }
       else if(this.user.colletedPoints >= 5000 && this.user.customerType.name === 'SILVER'){
          const customerType={
            id : this.user.username,
            name: 'GOLD',
            discount: 0.5,
            minPoints : 5000
          }
        console.log(customerType)
        this.updateCustomerType(customerType)
      }
    },
    updateCustomerType(customerType){

      axios.put('http://localhost:8080/FitnessCenter/rest/customers/customer-type', customerType)
          .then(
              response => {
                this.user = response.data
                console.log(this.user)
              }
          )
    },
    deactivateMembership(){
      axios.put('http://localhost:8080/FitnessCenter/rest/memberships/', this.membership)
          .then(
              response => {
                this.membership = response.data
              }
          )
    },
    getCurrentMembershipForCustomer(){
      axios.get('http://localhost:8080/FitnessCenter/rest/memberships/'+ this.user.username)
          .then(
              response => {
                this.membership = response.data
                console.log(this.membership.active)
                if(this.membership.id != null){
                  const now = new Date()
                  console.log(this.membership.endDate)
                  if(this.membership.endDate < now){
                    console.log('bbbb')
                    this.membership.isActive = false
                    this.membership.active = false
                    this.deactivateMembership()
                    this.countCollectedPoints()
                }
                }
              }
          )
    },
    GetCurrentUser(){
      axios.get('http://localhost:8080/FitnessCenter/rest/login/loggedUser')
          .then(response =>{
            this.user =response.data;
            if(this.user.userRole === 'CUSTOMER'){
              console.log('aaaa')
             this.getCurrentMembershipForCustomer()
              this.checkCollectedPoints()
            }
          })
    },
    logOut()
    {
      this.loggedIn = false;
      this.user.userRole = '';
      this.$router.push({name:"login"})
    }
  },
  setup(){
    return { sidebarWidth }
  }

}
</script>