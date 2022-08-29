<template>
  <div class="container-sm">
    <div class="row">
      <div class="col">
      <table class="justify-content-end">
        <tr>
          <td class="td-style title">
            <label>  {{training.name}}</label>
          </td>
        </tr>
        <tr>
          <td class="td-style">
            <label>Type:</label>
          </td>
          <td class="td-style">
            <label>{{training.type}}</label>
          </td>
        </tr>
        <tr>
          <td class="td-style">
            <label>Duration:</label>
          </td>
          <td class="td-style">
            <label>{{training.duration}}</label>
          </td>
        </tr>
        <tr>
          <td class="td-style">
            <label>Description:</label>
          </td>
          <td class="td-style">
            <label>{{training.description}}</label>
          </td>
        </tr>
        <tr>
          <td class="td-style">
            <label>Trainer:</label>
          </td>
          <td class="td-style">
            <label>{{trainer.name}} {{trainer.surname}}</label>
          </td>
        </tr>
      </table>
      </div>
      <div class="col">
        <img  :src="getImgUrl(training.id)" :alt="training.name" class="ico size border border-white"/>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TrainingPreview",
  props:{
    training : Object
  },
  data(){
    return{
      trainer:{
        name: '',
        surname : ''
      }
    }
  },
  created() {
    if(this.training.trainerId !== ''){
      axios.get('http://localhost:8080/FitnessCenter/rest/trainers/' + this.training.trainerId)
          .then(
              result => {
                this.trainer = result.data
                console.log(this.trainer.name)
              }
          )
    }
  },
  methods:{
    getImgUrl(training){
      console.log(training)
      if(training === ''){
        return
      }
      let images = require.context('../assets/training/', false, /\.png$/);
      return images('./' + training + ".png")
    }
  }
}
</script>

<style scoped>
.td-style{
  text-align: left;
  font-size: 18px;
  font-weight: bold;
}
.size{
  width: 200px;
  height: 200px;
}
.title{
  font-size: 40px;
  font-weight: bold;
  margin-bottom: 50px;
  padding-bottom: 10px;
}

</style>