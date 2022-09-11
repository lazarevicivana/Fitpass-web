<template>
  <div class="container-fluid ">
    <div class="row ">
      <h1>{{sportFacility.name}}</h1>
    </div>
  </div>
  <div  v-if="!(manager.sportFacilityId === '')" class="row  ">
    <div class="col-lg-6">
      <div class="container-fluid div-style  ">
        <div class="row">
          <div class="col">
        <table>
          <tr><td> Location : {{sportFacility.location.street}} {{sportFacility.location.number}}, {{sportFacility.location.city}}</td></tr>
          <tr><td> Status : {{convertStatus(sportFacility)}}</td></tr>
          <tr><td>Type: {{sportFacility.type}}</td></tr>
          <tr><td> Average grade: {{sportFacility.averageGrade}}</td></tr>
          <tr><td>Open time: {{dateTime(sportFacility.openTime)}}</td></tr>
          <tr><td> Close time: {{dateTime(sportFacility.closeTime)}}</td></tr>
        </table>
          </div>
        </div>
        <div class="row ">
          <div class="col">
            <MapOne class="map" :latitude="sportFacility.location.latitude" :longitude="sportFacility.location.longitude"></MapOne>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <button class="btn btn-primary" @click="createTraining()" >Add training</button>
        </div>
        <div class="col">
          <button class="btn btn-primary" @click="createContent()">Add content</button>
        </div>
      </div>
    </div>
      <div class="col-lg-6">
        <div class="container-fluid  padding-style ">
        <img  :src="getImgUrl(sportFacility.sportFacilityId)" :alt="sportFacility.name" class="ico size "/>
      </div>

    </div>
    </div>
</template>

<script>
import axios from "axios";
import SportFacility from "@/components/SportFacility";

export default {
  name: "ManagerFacilityView",
  components: {MapOne, SportFacility},
  props:{
    user: Object
  },
  data(){
    return{
     manager:{
       username: '',
       password : '',
       name : '',
       surname : '',
       birthday : '',
       gender : '',
       userRole : '',
       deleted : false,
       banned : false,
       sportFacilityId: ''
     },
      sportFacility:{
       name:'',
        sportFacilityId: '',
        location: {
         street: '',
          number: '',
          city: '',
          longitude:0,
          latitude: 0
        }
      }
    }
  },
  created() {
    this.GetData()
  },
  methods:{
    convertStatus(sportFacility) {
      if (sportFacility.worikng === true)
        return "Open";
      else
        return "Closed";
    },
    dateTime(value) {
      return moment(value).format('hh:mm');
    },
    getImgUrl(facility){
      if(facility === ''){
        return
      }
      let images = require.context('../assets/', false, /\.png$/);
      return images('./' + facility + ".png")
    },
    getManagerFacility(sportFacilityId){
      if(this.manager.sportFacilityId != ''){
        axios.get('http://localhost:8080/FitnessCenter/rest/facilities/'+sportFacilityId)
            .then(
                result => {
                  this.sportFacility = result.data
                  console.log(this.sportFacility)
                })
      }
    },
    GetData(){
      axios.get('http://localhost:8080/FitnessCenter/rest/managers/'+this.user.username)
          .then(
              result => {
                this.manager = result.data
                this.getManagerFacility(this.manager.sportFacilityId)

              })
    },
    createTraining(){
      this.$router.push(`/${this.sportFacility.sportFacilityId}/add-training`);
    },
    createContent(){
      this.$router.push(`/${this.sportFacility.sportFacilityId}/add-content`);
    }
  }
}
import moment from "moment";
import MapOne from "@/components/MapOne";
</script>

<style scoped>

.padding-style{
  padding-top: 20px;
  margin-top: 40px;
  margin-left: 150px;
  background: #BBBBBB;
  border-radius: 25px;
  border: 2px solid;
  border-color: white;
  color: black;
  width: 80%;
  padding-left: 40px;
  text-align: left;
  font-size: 18px;
}
table,img{
  margin-bottom: 30px;
  padding-bottom: 20px;
  margin-top: 18px;
  font-size: 18px;
}
h1{
  font-size: 50px;
  font-weight: bolder;
  margin: 15px;
}
.size{
  width: 95%;
  height: 105%;
}
button{
  color: white;
  background: #2c3e50;
  border-radius: 15px;
  border: none;
  font-size: 24px;
  width: 90%;
  margin-top: 20px;
  margin-bottom: 40px;
  height:70%;
  margin-left: 50px;
  display: block;
  position: relative;
}
.div-style{
  padding-top: 20px;
  margin-top: 40px;
  border-radius: 25px;
  color: black;
  width: 80%;
  padding-left: 40px;
  text-align: left;
  font-size: 17px;
}
table{
  font-size: 30px;
  font-weight: bold;
}
.map{
  width: 120% !important;
  height: 200px!important;
}

</style>