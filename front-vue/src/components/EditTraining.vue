<template>
  <form @submit.prevent="EditTrainingSubmit" >
    <h1>Add training</h1>
    <label>Name:</label>
    <input type="text" v-model="training.name" required >
    <label>Type:</label>
    <select v-model="training.type" required>
      <option>GROUP</option>
      <option>PERSONAL</option>
      <option>AEROBIC</option>
      <option>CARDIO</option>
      <option>GYM</option>
      <option>YOGA</option>
      <option>HIIT</option>
      <option>STRENGTH</option>
      <option>DANCE</option>
    </select>
    <label>Trainer:</label>
    <select v-model="selectedTrainer.username"  @change="onChange()">
      <option></option>
      <option v-for="trainer in trainers" v-bind:key="trainer.username" v-bind:value="trainer.username">{{trainer.name}} {{trainer.surname}}</option>
    </select>
    <label>Duration:</label>
    <input type="number" v-model="training.duration" >
    <label>Price:</label>
    <input type="number" v-model="training.price">
    <label>Description:</label>
    <input type="text" v-model="training.description"  >
    <div class="row">
      <div class="col">
        <label>Picture:</label>
        <input type="file" id="formFile" >
      </div>
      <div class="col ">
        <button @click.prevent="OnFileUpload" class="btn btn-primary mb-3 btn-lg  button-padding">Add photo</button>
      </div>
    </div>
    <input  type="submit" class="btn btn-primary button-basic" value="Edit"/>
  </form>
</template>
<script>
import axios from "axios";

export default {
  name: "EditTraining",
  data(){
    return {
      training:{
        id: '',
        name:'',
        type: '',
        sportFacilityId:'',
        duration:'',
        trainerId:'',
        description:'',
        price:'',
        deleted: false
      },
      trainers:[],
      selectedTrainer :{}
    }
  },
  created() {
    this.training.id = this.$route.params.id;
    axios.get('http://localhost:8080/FitnessCenter/rest/trainings/get-by/' + this.training.id)
        .then(
            result =>{
              this.training = result.data
              if(this.training.trainerId != null){
                this.getTrainers();
              }
            }
        )
  },
  methods:{
    OnFileUpload(){
      axios.post("http://localhost:8080/FitnessCenter/rest/files/uploadPhoto",this.training.id)
          .then((response)=>{console.log("Success set up name" + response)})
          .catch((error) => console.log(error))
      let base64String = "";
      const file = document.querySelector('input[type=file]')['files'][0];
      const reader = new FileReader();
      reader.onload = function () {
        base64String = reader.result.replace("data:", "").replace(/^.+,/, "");
        axios.post("http://localhost:8080/FitnessCenter/rest/files/uploadTrainingPhoto",base64String)
            .then((response)=>{console.log("Success uploading")})
            .catch((error) => console.log(error))
      }
      reader.readAsDataURL(file);
    },
    EditTrainingSubmit(){
      this.training.trainerId =this.selectedTrainer.username;
      axios.put('http://localhost:8080/FitnessCenter/rest/trainings/',this.training)
          .then(
              result =>{
                console.log(result.data)
              }
          )
      this.$router.push('/success-training-edit')
    },
    getTrainers(){
      axios.get('http://localhost:8080/FitnessCenter/rest/trainers/')
          .then(
              result => {
                this.trainers = result.data
                this.getTrainer();
              }
          )
    },
    getTrainer(){
      axios.get('http://localhost:8080/FitnessCenter/rest/trainers/'+ this.training.trainerId)
          .then(
              result => {
                this.selectedTrainer = result.data
              }
          )
    },
    onChange(){

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
  margin-top: 50px;
}
</style>