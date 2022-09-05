<template>
  <form @submit.prevent="onBuy()">
    <h1>Buy training</h1>
    <label>Date:</label>
    <input type="date" v-model="trainingHistory.signDate">
    <label>Time:</label>
    <input type="time" v-model="trainingHistory.timeOfSign" >

    <input  type="submit" class="btn btn-primary button-basic" value="Buy"/>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "BuyTrainingComponent",
  props:{
        user:Object,
  },
  data(){
    return{
      trainingHistory: {
        customerId: '',
        trainerId: '',
        trainingId: '',
        timeOfSign : '',
        signDate : Date
      },
      training: {},
      membership: {}
    }
  },
  created() {
    this.getActiveMembership()
    axios.get('http://localhost:8080/FitnessCenter/rest/trainings/get-by/' + this.$route.params.id)
        .then(
            result => {
              this.training = result.data
              console.log(this.training)
            }
        )
  },
  methods:{
    onBuy(){
      if((this.membership.id != null) && (this.membership.numberOfTrainings - this.membership.usedTrainings) > 0){
        this.trainingHistory.customerId = this.user.username
        this.trainingHistory.trainingId = this.$route.params.id
        this.trainingHistory.trainerId = this.training.trainerId
        ++this.membership.usedTrainings
        console.log(this.membership.usedTrainings)
        axios.post('http://localhost:8080/FitnessCenter/rest/training-history', this.trainingHistory)
            .then(
                result => {
                  console.log(result.data)
                  this.increseUsedTrainings()
                }
            )
      }
    },
    increseUsedTrainings(){
      axios.put('http://localhost:8080/FitnessCenter/rest/memberships/used-trainings', this.membership)
          .then(
              result => {
                this.membership = result.data
                console.log(this.membership)
              }
          )
    },
    getActiveMembership(){
      axios.get('http://localhost:8080/FitnessCenter/rest/memberships/' + this.user.username)
          .then(
              result => {
                this.membership = result.data
                console.log(this.membership)
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
</style>