<template>
  <div class="container-fluid ">
    <div class="row ">
      <h1>{{sportFacility.name}}</h1>
    </div>
  </div>
  <div  v-if="!(manager.sportFacilityId === '')" class="row  ">
    <div class="col-lg-6">
      <div class="container-fluid div-style  ">
        <table>
          <tr><td> Location : {{sportFacility.location}}</td></tr>
          <tr><td> Status : {{convertStatus(sportFacility)}}</td></tr>
          <tr><td>Type: {{sportFacility.type}}</td></tr>
          <tr><td> Average grade: {{sportFacility.averageGrade}}</td></tr>
          <tr><td>Open time: {{dateTime(sportFacility.openTime)}}</td></tr>
          <tr><td> Close time: {{dateTime(sportFacility.closeTime)}}</td></tr>
        </table>
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
  components: {SportFacility},
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
        sportFacilityId: ''
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
</script>

<style scoped>
.colorDiv{
  background: #B2C8DF;
  border-radius: 25px;
  border: 2px solid;
  padding: 20px;
  color: white;
  width: 70%;
}
.padding-style{
  padding-top: 20px;
  margin-top: 40px;
  background: #BBBBBB;
  border-radius: 25px;
  border: 2px solid;
  border-color: white;
  color: black;
  width: 80%;
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
  font-size: 20px;
}
h1{
  font-size: 60px;
  font-weight: bolder;
}
.size{
  width: 300px;
  height: 300px;
  margin: 40px;
}
button{
  color: white;
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


</style>