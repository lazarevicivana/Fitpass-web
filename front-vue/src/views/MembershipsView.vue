<template>
<div>
  <h1>Membership packages</h1>
  <div class="row row-style gy-4 row-cols-2 align-items-center" >
    <div v-for="membership in memberships">
      <div class="col-lg-8 colorDiv">
        <MembershipComponent :membership="membership"></MembershipComponent>      </div>
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
</style>