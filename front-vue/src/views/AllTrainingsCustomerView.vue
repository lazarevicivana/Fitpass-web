<template>
  <div class="container-fluid margin-style">
    <h1 class="padding-style">Trainings</h1>
    <div class="row padding-style">
      <div class="col">
        <input type="text"  placeholder="search">
      </div>
      <div class="col">
        <select >
          <option>All</option>
          <option>Customer</option>
          <option>Trainer</option>
          <option>Manager</option>
          <option>Admin</option>
        </select>
      </div>
      <div class="col">
        <select v-model="filter" @change="FilterUsers">
          <option>Sort</option>
          <option>Name asc</option>
          <option>Name desc</option>
          <option>Surname asc</option>
          <option>Surname desc</option>
          <option>Username asc</option>
          <option>Username desc</option>
        </select>
      </div>
    </div>
    <div class="row row-style gy-4 row-cols-2 align-items-center" >
      <div v-for="training in trainingsView" >
        <div class="col-lg-8 colorDiv" >
          <div class="row">
            <TrainingPreview :training="training"></TrainingPreview>
          </div>
          <div class="row">
            <label>Date:</label>
            <label>{{dateFormat(training.signDate)}}</label>
          </div>
          <div class="row">
            <label>Time:</label>
            <label>{{training.timeOfSign.hour}}: {{training.timeOfSign.minute}}</label>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import TrainingPreview from "@/components/TrainingPreview";
import trainingPreview from "@/components/TrainingPreview";
import moment from "moment";

export default {
  name: "AllTrainingsCustomerView",
  components: {TrainingPreview},
  props: {
    user: Object
  },
  data(){
    return{
      customer:{
        username: '',
        password : '',
        name : '',
        surname : '',
        birthday : '',
        gender : '',
        userRole : '',
        deleted : false,
        banned : false,
        sportFacilityId: '',
        customerType : '',
        collectedPoints : '',
        visitedFacilities : ''
      },
      trainingsHistory :[],
      trainingsView:[]
    }
  },
  created() {
    this.getData();
  },
  methods: {
    getData(){
      axios.get('http://localhost:8080/FitnessCenter/rest/customers/'+this.user.username)
          .then(
              result => {
                this.customer = result.data
                if(this.customer.username != ''){
                  this.getCustomerTrainingHistory(this.customer.username)
                }
              })
    },
    getCustomerTrainingHistory(customerId){
      axios.get('http://localhost:8080/FitnessCenter/rest/training-history/customer/'+ customerId)
          .then(
              result => {
                this.trainingsHistory = result.data
                this.trainingsHistory.forEach((training) =>{
                      this.getTrainings(training)
                    }
                )
              }
          )
    },
    getTrainings(training){
      axios.get('http://localhost:8080/FitnessCenter/rest/trainings/training-view/'+training.trainingId)
          .then(
              result => {
                const trainingView = result.data
                trainingView.signDate = training.sign
                trainingView.timeOfSign = training.timeOfSign
                console.log(training.timeOfSign)
                const t = []
                this.trainingsView.push(trainingView)
                console.log(this.trainingsView)
              }
          )
    },
    dateFormat(value){
      return moment(value).format('YYYY-MM-DD')
    }

  }
}
</script>

<style scoped>
.colorDiv{
  background: #072238;
  border-radius: 25px;
  border: 2px solid;
  width: 100%;
  padding: 20px;
  color: white;
}
.margin-style{
  margin-left: 180px;
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