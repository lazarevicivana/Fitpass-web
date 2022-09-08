<template>
  <div class="container-fluid ">
    <div class="row ">
      <h1>{{sportFacility.name}}</h1>
    </div>
  </div>
  <div class="row ">
    <div class="col-lg-6">
      <div class="container-fluid div-style  ">
        <div class="row">
          <div class="col">
            <table>
              <tr><td> Location : {{sportFacility.street}} {{sportFacility.number}}, {{sportFacility.city}}</td></tr>
              <tr><td> Status : {{convertStatus(sportFacility)}}</td></tr>
              <tr><td>Type: {{sportFacility.type}}</td></tr>
              <tr><td> Average grade: {{sportFacility.averageGrade}}</td></tr>
              <tr><td>Open time: {{sportFacility.openTime}}</td></tr>
              <tr><td> Close time: {{sportFacility.closeTime}}</td></tr>
            </table>
          </div>
        </div>
        <div class="row ">
          <div class="col">
            <MapOne class="map" :latitude="sportFacility.latitude" :longitude="sportFacility.longitude"></MapOne>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <button class="btn btn-primary" v-if="!viewComments" @click="onView(sportFacility.sportFacilityId)">View comments</button>
            <button class="btn btn-primary" v-if="viewComments" @click="onHide(sportFacility.sportFacilityId)">Hide comments</button>
          </div>
          <div class="col">
            <button class="btn btn-primary"  v-if="leaveComment" @click="onLeave(sportFacility.sportFacilityId)">Leave a comment</button>
          </div>
        </div>
      </div>
    </div>
    <div class="col-lg-6">
      <div class="container-fluid  padding-style ">
        <img  :src="getImgUrl(sportFacility.sportFacilityId)" :alt="sportFacility.name" class="ico size "/>
    </div>
    </div>

    <div class="row">
      <ViewComments v-if="viewComments" :user="user" :facilityId="sportFacility.sportFacilityId"></ViewComments>
    </div>
    <div class="row subtitle">
      <h2>Trainings</h2>
    </div>
    <div class="row row-style  row-cols-2 align-items-center">
      <div v-for="training in this.trainings" class="colorDiv">
        <div class="col ">
          <div class="row ">
            <TrainingPreview :training="training"></TrainingPreview>
          </div>
          <div class="row ">
            <button class="btn btn-primary " v-if="customer()" @click="onBuy(training.id)" >Buy</button>
          </div >

        </div >

      </div>
    </div>
  </div>


</template>

<script>
import SportFacilitieDetailPage from "@/components/SportFacilitieDetailPage";
import SportFacility from "@/components/SportFacility";
import TrainingPreview from "@/components/TrainingPreview";
import axios from "axios";
import moment from "moment";
import sportFacility from "@/components/SportFacility";
import ViewComments from "@/components/ViewComments";
import MapOne from "@/components/MapOne";
export default {
  name: "FacilitiesDetail",
   props:{ user: Object},
  data(){
    return{
     sportFacility:{
       sportFacilityId: '',
       name : '',
       street : '',
       city: '',
       number: '',
       isWorking : false,
       type : '',
       worikng:false,
       closeTime:'',
       openTime:'',
       averageGrade:'',
       facilityContent:[],
       latitude: 0,
       longitude: 0
    },trainings:[],
      viewComments: false,
      leaveComment: false
    }
  },
  components: {MapOne, ViewComments, SportFacility, SportFacilitieDetailPage,TrainingPreview},
  created() {
    console.log(this.sportFacility.name);
    this.sportFacility = JSON.parse(this.$route.params.data);
    this.getFacilityTrainings(this.sportFacility.sportFacilityId)
    if(this.user.userRole === 'CUSTOMER'){
      axios.get('http://localhost:8080/FitnessCenter/rest/training-history/get-by-customer/'+ this.user.username)
          .then(
              result => {
                this.leaveComment = result.data
                console.log(this.leaveComment)
              }
          )
    }
},methods: {
    convertStatus(sportFacility) {
      if (sportFacility.worikng === true)
        return "Open";
      else
        return "Closed";
    },
    getFacilityTrainings(sportFacilityId){
      axios.get('http://localhost:8080/FitnessCenter/rest/trainings/'+ sportFacilityId)
          .then(
              result => {
                this.trainings = result.data
                console.log(this.trainings.length)
              }
          )
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
    customer(){
      return this.user.userRole === 'CUSTOMER'
    },
    onBuy(id){
      this.$router.push(`/${id}/buy-training`);
    },
    onLeave(id){
      this.$router.push(`/${id}/comment`);
    },
    onView(id){
      this.viewComments = true
    },
    onHide(id){
      this.viewComments = false
    }
  }
}
</script>

<style scoped>
.colorDiv{
  background: #072238;
  border-radius: 25px;
  border: 2px solid;
  padding: 40px;
  color: white;
}
.subtitle{
  margin-top: 40px;
  font-weight: bolder;
}
.padding-style{
  padding-top: 20px;
  margin-top: 40px;
  background: #BBBBBB;
  border-radius: 25px;
  border: 2px solid;
  border-color: white;
  color: black;
  padding-left: 40px;
  text-align: left;
  font-size: 17px;
}
.row-style{
  margin-left: 40px;

}
table,img{
  margin-bottom: 30px;
  padding-bottom: 20px;
  margin-top: 20px;
  font-size: 24px;
  font-weight: bold;
}
h1{
  font-size: 60px;
  font-weight: bolder;
}
.map{
  width: 100% !important;
  height: 200px!important;
}
.size{
  width: 90%;
  height: 400px;
  margin: 20px
}

.div-style{
  padding-top: 20px;
  margin-top: 40px;
  border-radius: 25px;
  color: black;
  width: 80%;
  text-align: left;
  font-size: 17px;
}
h2{
  font-weight: bold;
}
.col-style{
  margin-left: 10px;
}
button{
  color: white;
  background: #2c3e50;
  border-radius: 15px;
  border: none;
  font-size: 24px;
  width: 90%;
  margin-top: 30px;
  height:90%;
  margin-left: 10px;
  display: block;
  position: relative;
}

</style>