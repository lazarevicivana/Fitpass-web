<template>
  <div class="container-fluid">
    <h1 class="padding-style">View trainers</h1>
    <div class="row padding-style">
    </div>
    <div class="row row-style gy-4 row-cols-3 align-items-center" >
    <div v-for="trainer in trainers">
      <div class="col-lg-8 colorDiv">
        <user-preview-component :user="trainer"></user-preview-component>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import axios from "axios";
import UserPreviewComponent from "@/components/UserPreviewComponent";

export default {
  name: "ManagerTrainersView",
  components:{UserPreviewComponent},
  props:{
    user: Object
  },
  data(){
    return{
      manager : {},
      trainers: []
    }
  },
  created() {
    axios.get('http://localhost:8080/FitnessCenter/rest/managers/'+this.user.username)
        .then(
            result => {
              this.manager = result.data
              this.getFacilityTrainers(this.manager.sportFacilityId)
            })
  },
  methods:{
    getFacilityTrainers(sportFacilityId){
      axios.get('http://localhost:8080/FitnessCenter/rest/trainers/facility/' + sportFacilityId)
          .then(
              result => {
                this.trainers = result.data
                console.log(this.trainers)
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