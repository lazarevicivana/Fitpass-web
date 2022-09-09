<template>
  <div class="container margin-style">
    <h1 class="padding-style">Trainings</h1>
    <div class="row padding-style">
      <div class="col">
        <input type="number" v-model="minPrice" placeholder="min price" @change="filterTrainings()" >
      </div>
      <div class="col">
        <input type="number"  v-model="maxPrice" placeholder="max price" @change="filterTrainings()" >
      </div>
      <div class="col">
        <input type="text"  v-model="search" placeholder="search" @change="filterTrainings()" >
      </div>

      <div class="col">
        <select v-model="sort" @change="filterTrainings">
          <option>Sort</option>
          <option>Price asc</option>
          <option>Price desc</option>
          <option>Date asc</option>
          <option>Date desc</option>
          <option>Facility asc</option>
          <option>Facility desc</option>
        </select>
      </div>
      <div class="col">
        <select v-model="filter" @change="filterTrainings">
          <option>All</option>
          <option>GROUP</option>
          <option>PERSONAL</option>
          <option>AEROBIC</option>
          <option>CARDIO</option>
          <option>GYM</option>
          <option>YOGA</option>
          <option>HIIT</option>
          <option>STRENGTH</option>
          <option>DANCE</option>
          <option>POOL</option>
          <option>SPORTCENTER</option>
          <option>DANCINGSTUDIO</option>
          <option>GYM</option>
          <option>BOWLINGCENTER</option>
          <option>SHOOTINGRANGE</option>
        </select>
      </div>
    </div>
    <div class="row padding-style">
      <div class="col">
        <input type="date" v-model="minDate"  @change="filterTrainings()" >
      </div>
      <div class="col">
        <input type="date"  v-model="maxDate"  @change="filterTrainings()" >
      </div>
    </div>
    <div class="row row-style gy-4 row-cols-2 align-items-center" >
      <div v-for="training in trainings" >
        <div class="col-lg-8 colorDiv" v-if="!training.canceled">
          <TrainingPreview :training="training"></TrainingPreview>
        </div>
      </div>
    </div>
    <hr>
          <h2 class="padding">Scheduled trainings:</h2>
    <div class="row row-style align-items-center">
        <div v-for="training in this.filterTrainings()">
          <div class="col colorDiv2" v-if="!training.canceled" >
            <div class="row">
              <div class="col">
                <TrainingPreview :training="training" ></TrainingPreview>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <table class="justify-content-end" >
                  <tr>
                    <td  class="td-style title"> <label>Date:</label></td>
                    <td class="td-style title">
                      <label class="label">{{dateFormat(training.signDate)}}</label>
                    </td>
                  </tr>
                  <tr>
                    <td  class="td-style title"> <label>Time:</label></td>
                    <td class="td-style title">
                      <label class="label">{{training.timeOfSign.hour}}: {{training.timeOfSign.minute}}</label>
                    </td>
                  </tr>
                  <tr>
                    <td  class="td-style title">  <label>Facility:</label></td>
                    <td class="td-style title">
                      <label class="label">{{training.facility.name}}</label>
                    </td>
                  </tr>
                  <tr>
                  <td  class="td-style title"> <label>Type:</label></td>
                  <td class="td-style title">
                    <label class="label">{{training.facility.type}}</label>
                  </td>
                </tr>
                </table>
              </div>
          </div>
            <button class="btn btn-primary" @click="onCancel(training)"><font-awesome-icon icon='xmark' />  Cancel</button>
        </div>
    </div>
    </div>
</div>
</template>

<script>
import axios from "axios";
import TrainingPreview from "@/components/TrainingPreview";
import moment from "moment";

export default {
  name: "AllTrainingsTrainerView",
  components: {TrainingPreview},
  props:{
    user: Object
  },
  data(){
      return{
        trainingHistory:{

        },
        trainer:{
          username: '',
          password : '',
          name : '',
          surname : '',
          birthday : '',
          gender : '',
          userRole : '',
          deleted : false,
          banned : false,
        },
        trainings :[],
        trainings2 :[],
        trainingsHistory :[],
        filter: 'All',
        filteredTrainings :[],
        sort: 'Sort',
        minPrice: 0,
        maxPrice: 0,
        search:'',
        minDate: '',
        maxDate: ''
      }
  },
  created() {
    this.getData();
    this.filteredTrainings = this.trainings;
  },
  methods:{
    getData(){
      axios.get('http://localhost:8080/FitnessCenter/rest/trainers/'+this.user.username)
          .then(
              result => {
                this.trainer = result.data
                this.getFacilityTrainings(this.trainer.username)
              })
    },
    getFacilityTrainings(trainerId){
      axios.get('http://localhost:8080/FitnessCenter/rest/trainings/trainer/'+ trainerId)
          .then(
              result => {
                this.trainings = result.data
                console.log(this.trainings)
                console.log('aaaaa')
                this.getTrainerTrainingHistory(this.trainer.username)

              }
          )
    },
    getTrainerTrainingHistory(trainerId){
      axios.get('http://localhost:8080/FitnessCenter/rest/training-history/trainer/'+ trainerId)
          .then(
              result => {
                this.trainingsHistory = result.data
                console.log('bbbbb')
                console.log(this.trainingsHistory )

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
                trainingView.signDate = training.signDate
                trainingView.timeOfSign = training.timeOfSign
                trainingView.canceled = training.canceled
                console.log('ccccc')
                const t = []

                console.log(this.trainings2)
                this.getFacility(trainingView)
              }
          )
    },
    onCancel(training){
      if (training.type === 'PERSONAL'){
        axios.get('http://localhost:8080/FitnessCenter/rest/training-history/get-by/'+ training.id)
            .then(
                result => {
                  this.trainingHistory = result.data
                  const nowDate = new Date(this.trainingHistory.signDate)
                  const now = new Date()
                  now.setDate(now.getDate() + 2)
                  const compare = nowDate > now
                  console.log(compare)
                  if(nowDate > now) {
                    axios.put('http://localhost:8080/FitnessCenter/rest/training-history/cancel', this.trainingHistory.id)
                        .then(
                            result => {
                              this.trainingHistory = result.data
                              training.canceled = this.trainingHistory.canceled
                              this.trainings2.filter(t => !t.canceled)

                            }
                        )
                  }
                }
            )
      }
    },
    getFacility(trainingView){
      axios.get('http://localhost:8080/FitnessCenter/rest/facilities/' + trainingView.sportFacilityId)
          .then(
              response => {
                trainingView.facility = response.data
                this.trainings2.push(trainingView)
                this.filteredTrainings.push(trainingView)
              }
          )
    },
    dateFormat(value){
      return moment(value).format('YYYY-MM-DD')
    },
    searchTrainings() {
      if (this.maxPrice !== 0 && this.maxPrice.toString() !== '') {
        if(this.maxDate !== '' && this.minDate !== '' ){
          return this.trainings2.filter(training => (this.maxPrice >= training.price)
              && (training.price >= this.minPrice)
              && ((moment(training.signDate)).isBefore(moment(this.maxDate)))
              && ((moment(training.signDate)).isAfter(moment(this.minDate)))
              && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
        }else if(this.minDate !== '' && this.maxDate === '' ){
          return this.trainings2.filter(training => (this.maxPrice >= training.price)
              && (training.price >= this.minPrice)
              && ((moment(training.signDate)).isAfter(moment(this.minDate)))
              && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
        }else if(this.minDate === '' && this.maxDate !== '' ){
          return this.trainings2.filter(training => (this.maxPrice >= training.price)
              && (training.price >= this.minPrice)
              && ((moment(training.signDate)).isBefore(moment(this.maxDate)))
              && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
        }
        return this.trainings2.filter(training => (this.maxPrice >= training.price)
            && (training.price >= this.minPrice)
            && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
      }
      if(this.maxDate !== '' && this.minDate !== '' ){
        return this.trainings2.filter(training => (training.price >= this.minPrice)
            && ((moment(training.signDate)).isBefore(moment(this.maxDate)))
            && ((moment(training.signDate)).isAfter(moment(this.minDate)))
            && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
      }else if(this.minDate !== '' && this.maxDate === '' ){
        return this.trainings2.filter(training =>  (training.price >= this.minPrice)
            && ((moment(training.signDate)).isAfter(moment(this.minDate)))
            && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
      }else if(this.minDate === '' && this.maxDate !== '' ){
        return this.trainings2.filter(training => (training.price >= this.minPrice)
            && ((moment(training.signDate)).isBefore(moment(this.maxDate)))
            && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
      }
      return this.trainings2.filter(training => training.price >= this.minPrice && this.search.toLowerCase()
          .split(' ').every(s => training.facility.name.toLowerCase().includes(s)))
    },
    sortTrainings(){
      if(this.sort === 'Price asc'){
        this.filteredTrainings.sort((a,b) => (a.price < b.price) ? -1 : 1)
      }else  if(this.sort === 'Price desc'){
        this.filteredTrainings.sort((a,b) => (a.price < b.price) ? 1 : -1)
      }
      else  if(this.sort === 'Date asc'){
        this.filteredTrainings.sort((a,b) => (a.signDate < b.signDate) ? -1 : 1)
      }
      else  if(this.sort === 'Date desc'){
        this.filteredTrainings.sort((a,b) => (a.signDate < b.signDate) ? 1 : -1)
      } else  if(this.sort === 'Facility asc'){
        this.filteredTrainings.sort((a,b) => (a.facility.name < b.facility.name) ? -1 : 1)
      }
      else  if(this.sort === 'Facility desc'){
        this.filteredTrainings.sort((a,b) => (a.facility.name < b.facility.name) ? 1 : -1)
      }
    },
    filterTrainings(){
      this.filteredTrainings = this.searchTrainings()
      if(this.filter !== 'All'){
        this.filteredTrainings = [...this.filteredTrainings.filter(training =>
            training.type.toLowerCase().includes(this.filter.toLowerCase())
            || training.facility.type.toLowerCase().includes(this.filter.toLowerCase()))]

      }
      this.sortTrainings()
      return this.filteredTrainings
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
  margin-left: 20px;
}
.padding-style{
  margin-bottom: 80px;
}
.padding{
  margin-bottom: 30px;
  margin-top: 30px;
}
.row-style{
  padding-bottom: 100px;
  margin-bottom: 30px;
}

button{
  background: #072238;
  color: white;
  border-radius: 10px;
  width: 50%;
  height: 50%;
}

.label-style{
  font-size: 20px;
  font-weight: bold;
  color: white;
  background: #87182b;
  width: 50%;
  height: 50%;
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
.colorDiv2{
   background: #BBBBBB;
   border-radius: 25px;
   border: 2px solid;
   width: 100%;
   padding: 20px;
   color: black;
   margin-bottom: 40px;
 }
.td-style{
  text-align: left;
  font-size: 18px;
  font-weight: bold;
}
table{
  margin-left: 20px;
}
.label{
  margin-left: 70px;
}
h1{
  font-weight: bolder;
  font-size: 70px;
}
h2{
  font-weight: bold;
}
</style>