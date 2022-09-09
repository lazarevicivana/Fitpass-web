<template>
  <div class="container-fluid margin-style">
    <h1 class="padding-style">Trainings</h1>
    <div class="row padding-style">
      <div class="col">
        <input type="number" v-model="minPrice" placeholder="min price"  >
      </div>
      <div class="col">
        <input type="number"  v-model="maxPrice" placeholder="max price"  >
      </div>
      <div class="col">
        <select v-model="sort" @change="FilterTrainings">
          <option>Sort</option>
          <option>Price asc</option>
          <option>Price desc</option>
          <option>Date asc</option>
          <option>Date desc</option>
        </select>
      </div>
      <div class="col">
        <select v-model="filter" @change="FilterUsers">
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
        </select>
      </div>
    </div>
  <div class="row row-style gy-4 row-cols-2 align-items-center" >
    <div v-for="training in this.FilterTrainings()">
      <div class="col colorDiv">
        <TrainingPreview :training="training"></TrainingPreview>
        <button class="btn btn-primary" @click="onEdit(training)"><font-awesome-icon icon="pen-clip"/>  Edit</button>
      </div >
    </div>
  </div>
 </div>
</template>

<script>
import axios from "axios";
import TrainingPreview from "@/components/TrainingPreview";

export default {
  name: "AllTrainingsManagerView",
  components: {TrainingPreview},
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
      trainings :[],
      filter: 'All',
      filteredTrainings :[],
      sort: 'Sort',
      minPrice: 0,
      maxPrice: 0
    }
  },
  created() {
    this.getData();
    this.filteredTrainings = this.trainings;
  },
  methods:{
    getData(){
      axios.get('http://localhost:8080/FitnessCenter/rest/managers/'+this.user.username)
          .then(
              result => {
                this.manager = result.data
                if(this.manager.sportFacilityId != ''){
                  this.getFacilityTrainings(this.manager.sportFacilityId)
                }
              })
    },
    getFacilityTrainings(sportFacilityId){
      axios.get('http://localhost:8080/FitnessCenter/rest/trainings/'+ sportFacilityId)
          .then(
              result => {
                this.trainings = result.data
              }
          )
    },
    searchTrainings(){
      console.log(this.maxPrice)
      console.log(this.minPrice)
      if(this.maxPrice !== 0  && this.maxPrice.toString() !== ''){
        return this.trainings.filter(training => training.price <= this.maxPrice && training.price >= this.minPrice)
      }
      return this.trainings.filter(training => training.price >= this.minPrice)
    },
    onEdit(training){
      this.$router.push(`/manager-trainings/${training.id}`);
    },
    FilterTrainings(){
      this.filteredTrainings = this.searchTrainings();
      if(this.filter !== 'All'){
        this.filteredTrainings = [...this.filteredTrainings.filter(training =>  training.type.toLowerCase().includes(this.filter.toLowerCase()))]
      }
      this.sortTrainings()
      return this.filteredTrainings
    },
    sortTrainings(){
      if(this.sort === 'Price asc'){
        this.filteredTrainings.sort((a,b) => (a.price < b.price) ? -1 : 1)
      }else  if(this.sort === 'Price desc'){
        this.filteredTrainings.sort((a,b) => (a.price < b.price) ? 1 : -1)
      }
      else  if(this.sort === 'Date asc'){
        this.filteredTrainings.sort((a,b) => (a.sr < b.price) ? -1 : 1)
      }
      else  if(this.sort === 'Price desc'){
        this.filteredTrainings.sort((a,b) => (a.price < b.price) ? 1 : -1)
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
h1{
  font-size: 70px;
  font-weight: bolder;
}
.margin-style{
  margin-left: 70px;
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
button{
  color: white;
  background: #2c3e50;
  border-radius: 15px;
  border: none;
  font-size: 24px;
  width: 90%;
  margin-top: 30px;
  height:90%;
  margin-left: 30px;
  display: block;
  position: relative;
}
</style>