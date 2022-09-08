<template>
  <form @submit.prevent="TrainingSubmit">
    <h1>Add training</h1>
    <label>Name:</label>
    <input type="text" v-model="training.name" required>
    <label>Type:</label>
    <select v-model="training.type" required >
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
    <select v-model="seletedValue" @change="onChange()" >
       <option v-for="trainer in trainers" :value="trainer">{{trainer.name}} {{trainer.surname}}</option>
    </select>
    <label>Duration:</label>
    <input type="number" v-model="training.duration">
    <label>Price:</label>
    <input type="number" v-model="training.price">
    <label>Description:</label>
    <input type="text" v-model="training.description">
    <div class="row">
      <div class="col">
        <label>Picture:</label>
        <input type="file" id="formFile" required >
      </div>
      <div class="col ">
        <button @click.prevent="OnFileUpload"  class="btn btn-primary mb-3 btn-lg  button-padding">Add photo</button>
      </div>
    </div>
    <input   type="submit" class="btn btn-primary button-basic" value="Add"/>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "CreateTraining",
  data(){
    return{
      training:{
        id: '',
        name:'',
        type: '',
        sportFacilityId:'',
        duration:'',
        trainerId:'',
        description:'',
        price: '',
        deleted: false
      },
      fileName: '',
      trainers: [],
      seletedValue: '',
      trainings:[],
      create: false,
      photoUpload: false,
      name: true
    }
  },
  created() {
    this.GetAll();
    axios.get('http://localhost:8080/FitnessCenter/rest/trainers/')
        .then(
            result => {
              this.trainers = result.data
            }
        )
  },
  methods:{
    TrainingSubmit(){
      this.name = true
      console.log(this.create)
      if(this.create === false){
        this.$notify({
          title: 'Error while creating training',
          type: 'error',
          text:"You must upload photo before creating a training!",
          closeOnClick: true

        })
        return;
      }
      this.trainings.forEach(t =>{
        if(t.name === this.training.name) {
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
        } })
        this.training.sportFacilityId = this.$route.params.id;
      if(this.seletedValue.username !== null){
        this.training.trainerId =this.seletedValue.username;
      }
        if(this.create && this.name){
          axios.post('http://localhost:8080/FitnessCenter/rest/trainings/create',this.training)
              .then(
                  result =>{
                    console.log(result.data)
                    this.$router.push('/success-training-create')
                  }
              )
        }
    },
    onChange(){

    },
    OnFileUpload(){
      this.create = true
      axios.post("http://localhost:8080/FitnessCenter/rest/files/uploadPhoto",this.trainings.length.toString())
          .then((response)=>{console.log("Success set up name" + response)})
          .catch((error) => console.log(error))
      let base64String = "";
      const file = document.querySelector('input[type=file]')['files'][0];
      const reader = new FileReader();
      reader.onload = function () {
        base64String = reader.result.replace("data:", "").replace(/^.+,/, "");
        axios.post("http://localhost:8080/FitnessCenter/rest/files/uploadTrainingPhoto",base64String)
            .then((response)=>{
              console.log("Success uploading")
            })
            .catch((error) => console.log(error))
      }
      reader.readAsDataURL(file);
    },
    GetAll(){
      axios.get("http://localhost:8080/FitnessCenter/rest/trainings/getAll")
          .then(
              result => {
                this.trainings = result.data
                console.log(this.trainings)
              }
          )
    }
  }
}
</script>

<style scoped>
form {
  max-width: 500px;
  margin: 30px auto;
  background: #072238;
  text-align: center;
  padding: 40px;
  border-radius: 10px;
  font-size: 15px;
  color: white;
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
  font-size: 15px;
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
   font-size: 20px;
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