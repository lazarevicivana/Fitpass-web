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
      <div v-for="training in trainings" >
        <div class="col-lg-8 colorDiv" v-if="!training.canceled">
          <TrainingPreview :training="training"></TrainingPreview>
          <button class="button-basic" @click="onCancel(training)">Cancel</button>
        </div>
        <div class="canceled">
          <div class="col-lg-8 color2Div" v-if="training.canceled">
            <TrainingPreview :training="training"></TrainingPreview>
            <label class="label-style">Canceled</label>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import TrainingPreview from "@/components/TrainingPreview";

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
        filter: 'All',
        filteredTrainings :[]
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
              }
          )
    },
    getTrainingHistory(trainingId){

    },
    onCancel(training){
      if (training.type === 'PERSONAL'){
        axios.get('http://localhost:8080/FitnessCenter/rest/training-history/get-by/'+ training.id)
            .then(
                result => {
                  this.trainingHistory = result.data
                  const now = new Date()
                  now.setDate(now.getDate() + 2)
                  if(this.trainingHistory.signDate > now){
                    axios.put('http://localhost:8080/FitnessCenter/rest/trainings/cancel', training)
                        .then(
                            result => {
                              training = result.data
                              this.trainings.filter(t => t.canceled === false)

                            }
                        )
                  }
                }
            )



      }

    },
    FilterTrainings(){
      this.filteredTrainings = this.trainings;
      if(this.filter !== 'All'){
        this.filteredTrainings = [...this.filteredTrainings.filter(training =>  training.type.toLowerCase().includes(this.filter.toLowerCase()))]
      }
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

.canceled{
  background: #5c494d;
  border-radius: 25px;
  opacity: 60%;
}

.color2Div{
  border-radius: 25px;
  border: 2px solid;
  width: 100%;
  padding: 20px;
  color: white;
}


.margin-style{
  margin-left: 100px;
}
.padding-style{
  margin-bottom: 80px;
}
.row-style{
  padding-bottom: 100px;
  margin-bottom: 30px;
}

.button-basic{
  background: #87182b;
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
</style>