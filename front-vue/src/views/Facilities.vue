<template>
<div class="container-md">
<h1 class="padding-style">All fitness centers</h1>
<!--  <div class="row"></div>-->
  <div class="row gy-2">
    <div class="col-sm"></div>
    <div class="col-sm">
      <input type="text"  v-model="search" placeholder="search">
    </div>
    <div class="col-sm"></div>
    <div class="col-sm">

    </div>
    <div class="col-sm"></div>

  </div>
  <div class="row row-style gy-2" v-for="sportFacility in filterSportsFacilities">
    <div class="col-sm"></div>
    <div class="col-sm">
    </div>
    <div class="col-sm"></div>

    <div  class="row gy-2 text-center colorDiv " >
<!--      <router-link to="/facilitiesDetail">-->
        <div  class="container-sm d-flex align-items-center justify-content-center" >

         <SportFacility @click="FacilitieDetail(sportFacility)" :sportFacility="sportFacility"/>

        </div>
<!--      </router-link>-->
    </div>

  </div>
</div>
</template>

<script>
import SportFacility from "@/components/SportFacility";
import axios from "axios";

export default {
  name: "Facilities",
  components: {SportFacility},
  data(){
    return{
      sportFacilities : [],
      search : ''
    }
  },
  created() {
    this.getAll()
  },
  computed:{
    filterSportsFacilities(){
      return this.sportFacilities.filter(sportFacility => sportFacility.name.toLowerCase().includes(this.search.toLocaleLowerCase()) || sportFacility.location.toLowerCase().includes(this.search.toLowerCase()) || sportFacility.averageGrade.toString().includes(this.search) || sportFacility.type.toLowerCase().includes(this.search.toLowerCase()) )
    }
  },
  methods:
  {
     getAll(){
      axios.get('http://localhost:8080/FitnessCenter/rest/facilities/GetAll')
          .then(response =>{
            this.sportFacilities =response.data
          })
     },
    FacilitieDetail(sportFacility)
    {
      console.log(sportFacility);
      this.$router.push({name:"facilitiesDetail",params:{data:JSON.stringify(sportFacility)}})
    }
  }
}

</script>

<style scoped>
.colorDiv{
  background: #072238;
  border-radius: 25px;
  border: 2px solid;
  padding: 20px;
  color: white;
}
.padding-style{
  margin-bottom: 80px;
}
.row-style{
  margin-left: 80px;
  width: 700px;
}

</style>