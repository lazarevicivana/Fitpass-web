<template>
<div>
  <h1 class="padding-style">Membership packages</h1>
  <div class="row row-style gy-4 row-cols-4 align-items-center" >
    <div v-for="membership in memberships">
      <div class="col-lg-8 colorDiv">
        <div class="row">
          <MembershipComponent :membership="membership"></MembershipComponent>
        </div>
        <div class="row ">
        <button v-if="customer()" @click="onBuy(membership.id)" class="btn btn-primary" >Buy</button>
      </div >
      </div>

    </div>
  </div>
</div>
</template>

<script>
import MembershipComponent from "@/components/MembershipComponent";
import axios from "axios";
export default {
  name: "MembershipsView",
  components: {MembershipComponent},
  props:{user: Object},
  data(){
    return{
      memberships:{}
    }
  },
  created() {
    axios.get('http://localhost:8080/FitnessCenter/rest/membership-package/')
        .then(
            response => {
              this.memberships = response.data
              console.log(this.memberships)
            }
        )
  },
  methods:{
    customer(){
      return this.user.userRole === 'CUSTOMER'
    },
    onBuy(id){
      this.$router.push(`/${id}/buy-membership`)
    }
  }
}
</script>

<style scoped>
.colorDiv{
  background: #072238;
  border-radius: 25px;
  border: 2px solid;
  padding: 10px;
  color: white;
  width: 105%;
  margin-left: 100px;
  margin-right: 50px;
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
input,select {
  display: block;
  padding-right: 10px;
  width: 50%;
  border: 1px solid black;
  box-sizing: border-box;
  color: #555;
  font-size: 20px;
  border-radius: 15px;
}
button{
   color: white;
   background: #2c3e50;
   margin-top: 40px;
   display: block;
   padding-right: 10px;
   margin-left: 10px;
   font-size: 24px;
   vertical-align: center;
   box-sizing: border-box;
   border: none;
   width: 60%;
   border-radius: 15px;
   position: relative;
   -ms-transform: translateX(-50%);
   transform: translateX(25%);
 }
h1{
  font-weight: bolder;
  font-size: 70px;
  margin-left: 100px;
}
</style>