<template>
  <div class="container-fluid margin-style">
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
      <div v-for="training in this.filterTrainings()" >
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
          <div class="row">
            <label>Facility:</label>
            <label>{{training.facility.name}}</label>
          </div>
          <div class="row">
            <label>Type:</label>
            <label>{{training.facility.type}}</label>
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
      trainingsView:[],
      filter: 'All',
      filteredTrainings :[],
      sort: 'Sort',
      minPrice: 0,
      maxPrice: 0,
      search:'',
      facilities:[],
      minDate: '',
      maxDate: ''
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
                trainingView.signDate = training.signDate
                console.log( typeof trainingView.signDate)
                trainingView.timeOfSign = training.timeOfSign
                this.getFacility(trainingView)


              }
          )
    },
    getFacility(trainingView){
      axios.get('http://localhost:8080/FitnessCenter/rest/facilities/' + trainingView.sportFacilityId)
          .then(
              response => {
                trainingView.facility = response.data
                this.trainingsView.push(trainingView)
                this.filteredTrainings.push(trainingView)
              }
          )
    },
    dateFormat(value){
      return moment(value).format('YYYY-MM-DD')
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
    },
    searchTrainings() {
      if (this.maxPrice !== 0 && this.maxPrice.toString() !== '') {
        if(this.maxDate !== '' && this.minDate !== '' ){
          return this.trainingsView.filter(training => (this.maxPrice >= training.price)
              && (training.price >= this.minPrice)
              && ((moment(training.signDate)).isBefore(moment(this.maxDate)))
              && ((moment(training.signDate)).isAfter(moment(this.minDate)))
              && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
        }else if(this.minDate !== '' && this.maxDate === '' ){
          return this.trainingsView.filter(training => (this.maxPrice >= training.price)
              && (training.price >= this.minPrice)
              && ((moment(training.signDate)).isAfter(moment(this.minDate)))
              && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
        }else if(this.minDate === '' && this.maxDate !== '' ){
          return this.trainingsView.filter(training => (this.maxPrice >= training.price)
              && (training.price >= this.minPrice)
              && ((moment(training.signDate)).isBefore(moment(this.maxDate)))
              && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
        }
        return this.trainingsView.filter(training => (this.maxPrice >= training.price)
                                                      && (training.price >= this.minPrice)
                                                      && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
      }
      if(this.maxDate !== '' && this.minDate !== '' ){
        return this.trainingsView.filter(training => (training.price >= this.minPrice)
            && ((moment(training.signDate)).isBefore(moment(this.maxDate)))
            && ((moment(training.signDate)).isAfter(moment(this.minDate)))
            && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
      }else if(this.minDate !== '' && this.maxDate === '' ){
        return this.trainingsView.filter(training =>  (training.price >= this.minPrice)
            && ((moment(training.signDate)).isAfter(moment(this.minDate)))
            && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
      }else if(this.minDate === '' && this.maxDate !== '' ){
        return this.trainingsView.filter(training => (training.price >= this.minPrice)
            && ((moment(training.signDate)).isBefore(moment(this.maxDate)))
            && (this.search.toLowerCase().split(' ').every(s => training.facility.name.toLowerCase().includes(s))))
      }
      return this.trainingsView.filter(training => training.price >= this.minPrice && this.search.toLowerCase()
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