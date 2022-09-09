<template>
  <form @submit.prevent="FacilitySubmit">
    <h1>Add facility</h1>
    <label v-if="!next">Name:</label>
    <input v-if="!next" type="text" v-model="facility.name" required>
    <label v-if="!next">Type:</label>
    <select v-if="!next" v-model="facility.type" required>
      <option>POOL</option>
      <option>SPORTCENTER</option>
      <option>DANCINGSTUDIO</option>
      <option>BOWLINGCENTER</option>
      <option>GYM</option>
      <option>SHOOTINGRANGE</option>
    </select>
    <label v-if="!next">Open time:</label>
    <input v-if="!next" type="time" v-model="facility.openTime" required>
    <label v-if="!next">Close time:</label>
    <input v-if="!next" type="time" v-model="facility.closeTime" required>
    <label v-if="!next">Street:</label>
    <input v-if="!next" type="text" v-model="facility.street" required>
    <label v-if="!next">Number:</label>
    <input v-if="!next" type="number" v-model="facility.number" required>
    <label v-if="!next">City:</label>
    <input v-if="!next" type="text" v-model="facility.city" required>
    <label v-if="!next">Postal code:</label>
    <input v-if="!next" type="number" v-model="facility.postalCode" required>
    <label v-if="!next">Longitude:</label>
    <input v-if="!next" type="text" v-model="facility.longitude" required>
    <label v-if="!next">Latitude:</label>
    <input v-if="!next" type="text" v-model="facility.latitude" required>
    <label v-if="available">Manager:</label>
    <select  v-if="available" v-model="selectedValue" required>
      <option></option>
      <option v-for="manager in availableManagers" :value="manager" >{{manager.username}}</option>
    </select>
    <div v-if="!next" class="row">
      <div class="col">
        <label>Picture:</label>
        <input type="file" id="formFile" required >
      </div>
      <div class="col ">
        <button @click.prevent="OnFileUpload"  class="btn btn-primary mb-3 btn-lg  button-padding">Add photo</button>
      </div>
    </div>
    <AddManagerForFacility :facility="facility" :id="id" v-if="next"></AddManagerForFacility>
    <input v-if="available" type="submit" class="btn btn-primary button-basic" value="Create"/>
    <input v-if="!available && !next"  type="submit" class="btn btn-primary button-basic" value="Next"/>
  </form>
</template>

<script>
import axios from "axios";
import AddManagerForFacility from "@/components/AddManagerForFacility";

export default {
  name: "CreateSportFacility",
  components: {AddManagerForFacility},
  data(){
    return{
      facility:{
        name:'',
        type:'',
        openTime:'',
        closeTime:'',
        longitude:'',
        latitude:'',
        street:'',
        city:'',
        isWorking:false,
        number: '',
        postalCode:'',
        averageGrade:'0'
      },
      facilities:[],
      availableManagers: [],
      available: false,
      next: false,
      selectedValue: '',
      id: 0,
      manager: {},
      uploadPhoto: false
    }
  },
  created() {
    this.GetAvailableManagers();
    this.GetAllFacilities();

},
  methods:{
    FacilitySubmit(){
      this.name = true
      if(this.uploadPhoto === false){
        this.$notify({
          title: 'Error while creating facility',
          type: 'error',
          text:"You must upload photo before creating a new facility!",
          closeOnClick: true
        })
        return;
      }
      this.facilities.forEach(f =>{
        if(f.name === this.facility.name){
          this.name = false
          if (this.name === false) {
            this.$notify({
              title: 'Error while creating training',
              type: 'error',
              text: "A training with that name already exists in database!",
              closeOnClick: true
            })
            return;
          }
        }})
      if(this.availableManagers.length > 0){
        if(this.name && this.uploadPhoto){
        axios.post('http://localhost:8080/FitnessCenter/rest/facilities/',this.facility)
            .then(
                  _ => {
                    this.GetAllFacilities();
                    this.selectedValue.sportFacilityId = this.id;
                      this.updateManagerFacilityId();
                      this.$router.push('/success-facility-create')
                }
            )}
      }
      else{
        this.next = true;
      }
    },
    GetAllFacilities(){
      axios.get('http://localhost:8080/FitnessCenter/rest/facilities/get-all-dto')
          .then(response =>{
            this.facilities = response.data
            this.id = this.facilities.length;

          })
    },
    updateManagerFacilityId(){
      axios.put('http://localhost:8080/FitnessCenter/rest/managers/updateFacilityId', this.selectedValue)
          .then(
              response => {
                this.manager = response.data
                console.log(this.manager)
              }
          )
    },
    OnFileUpload(){
      this.uploadPhoto = true
      axios.post("http://localhost:8080/FitnessCenter/rest/files/uploadPhoto",this.facilities.length.toString())
          .then((response)=>{console.log("Success set up name" + response)})
          .catch((error) => console.log(error))
      let base64String = "";
      const file = document.querySelector('input[type=file]')['files'][0];
      const reader = new FileReader();
      reader.onload = function () {
        base64String = reader.result.replace("data:", "").replace(/^.+,/, "");
        axios.post("http://localhost:8080/FitnessCenter/rest/files/uploadFacilityLogo",base64String)
            .then((response)=>{console.log("Success uploading")})
            .catch((error) => console.log(error))
      }
      reader.readAsDataURL(file);

    },
    GetAvailableManagers(){
      axios.get('http://localhost:8080/FitnessCenter/rest/managers/available-managers')
          .then(
              response => {
                this.availableManagers = response.data
                if(this.availableManagers.length){
                  this.available = true;
                }
              }
          )
    }
  }
}
</script>

<style scoped>
form {
  max-width: 600px;
  margin: 80px auto 30px 300px;
  background: #072238;
  text-align: center;
  padding: 40px;
  border-radius: 10px;
  font-size: 18px;
  color: white;
}h1{
   font-weight: bolder;
   font-size: 50px;
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
  font-size: 18px;
}input,select {
   display: block;
   padding-left: 10px;
   padding-right: 10px;
   margin-left: 70px;
   width: 70%;
   box-sizing: border-box;
   border: none;
   border-bottom: 1px solid #ddd;
   color: #555;
   font-size: 18px;
   border-radius: 15px;

 }
.button-basic{
  color: white;
  margin-top: 40px;
  display: block;
  padding-left: 10px;
  padding-right: 10px;
  margin-left: 55px;
  font-size: 24px;
  font-weight: bold;
  width: 50%;
  vertical-align: center;
  box-sizing: border-box;
  border: none;
  position: relative;
  -ms-transform: translateX(-50%);
  transform: translateX(25%);
}
.button-basic{
  margin-top: 40px;
}
.button-padding{
  margin-top: 70px;
}

</style>