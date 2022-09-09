<template>
  <form @submit.prevent="ContentSubmit">
    <h1>Add content</h1>
    <label>Name:</label>
    <input type="text" v-model="content.name" required>
    <label>Type:</label>
    <select v-model="content.type" required >
      <option>SPA</option>
      <option>POOL</option>
      <option>SAUNA</option>
    </select>
    <label>Duration:</label>
    <input type="number" v-model="content.duration">
    <label>Price:</label>
    <input type="number" v-model="content.price">
    <label>Description:</label>
    <input type="text" v-model="content.description">
    <div class="row">
      <div class="col">
        <label>Picture:</label>
        <input type="file" id="formFile" required >
      </div>
      <div class="col ">
        <button @click.prevent="OnFileUpload"  class="btn btn-primary mb-3 btn-lg  button-padding">Add photo</button>
      </div>
    </div>

    <input  type="submit" class="btn btn-primary button-basic" value="Add"/>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "CreateTraining",
  data(){
    return{
      content:{
        id: '',
        name:'',
        type: '',
        sportFacilityId:'',
        duration:'',
        description:'',
        price: '',
        deleted: false
      },
      fileName: '',
      isDisabled:true,
      contents :[],
      create: false,
      name: true
    }
  },
  created() {
    this.GetAll();
  },
  methods:{
    ContentSubmit(){
      this.name = true
      console.log(typeof this.content.duration)
      if(this.create === false){
        this.$notify({
          title: 'Error while creating content',
          type: 'error',
          text:"You must upload photo before creating a content!",
          closeOnClick: true
        })
        return;
      }
      this.contents.forEach(c =>{
        if(c.name === this.content.name){
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
      this.content.sportFacilityId = this.$route.params.id;
      if(this.name && this.create){
      axios.post('http://localhost:8080/FitnessCenter/rest/contents',this.content)
          .then(
              result =>{
                console.log(result.data)
                  this.$router.push('/success-content-create')
              }
          )
      }
    },
    onChange(){

    },
    OnFileUpload(){
      this.create = true
      axios.post("http://localhost:8080/FitnessCenter/rest/files/uploadPhoto",this.contents.length.toString())
          .then((response)=>{console.log("Success set up name" + response)})
          .catch((error) => console.log(error))
      let base64String = "";
      const file = document.querySelector('input[type=file]')['files'][0];
      const reader = new FileReader();
      reader.onload = function () {
        base64String = reader.result.replace("data:", "").replace(/^.+,/, "");
        axios.post("http://localhost:8080/FitnessCenter/rest/files/uploadContentPhoto",base64String)
            .then((response)=>{console.log("Success uploading")})
            .catch((error) => console.log(error))
      }
      reader.readAsDataURL(file);
    },
    GetAll(){
      axios.get("http://localhost:8080/FitnessCenter/rest/contents")
          .then(
              result => {
                this.contents = result.data
                console.log(this.contents)
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