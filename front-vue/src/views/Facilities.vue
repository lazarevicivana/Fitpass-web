<template>
<div class="container-lg margin-style ">
<h1 class="padding-style">All fitness centers</h1>
  <div class="row padding-style ">
      <input type="text" class="search"  v-model="search" placeholder="search">
  </div>
  <div class="row div ">
    <div class="col-4 scroll">
      <div class="row  row-style text-center" v-for="sportFacility in filterSportsFacilities">
        <div  class="col colorDiv" >
          <SportFacility @click="FacilitieDetail(sportFacility)" :sportFacility="sportFacility"/>
        </div>
      </div>
    </div>
    <div class="col map">
        <Map :facilities="filterSportsFacilities"></Map>
    </div>
  </div>
</div>
</template>

<script>
import SportFacility from "@/components/SportFacility";
import axios from "axios";
import Map from "@/components/Map";

export default {
  name: "Facilities",
  components: {
    SportFacility,
    Map
  },
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
      console.log(this.sportFacilities.forEach((s) => {console.log(s.averageGrade)}))
      console.log(this.sportFacilities.length)
      return this.sportFacilities.filter(sportFacility => sportFacility.name.toLowerCase().includes(this.search.toLowerCase()) || sportFacility.location.toLowerCase().includes(this.search.toLowerCase()) || sportFacility.averageGrade.toString().includes(this.search) || sportFacility.type.toLowerCase().includes(this.search.toLowerCase()) )
    }
  },
  methods:
  {
     getAll(){
      axios.get('http://localhost:8080/FitnessCenter/rest/facilities/get-all-dto')
          .then(response =>{
            this.sportFacilities = response.data
            console.log(this.sportFacilities)
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
.col-style{
  width: 100px;
}
.padding-style{
  margin-bottom: 80px;
}
.div{
 width: 1800px;
}
.row-style{
  width: 600px;
  margin-bottom: 60px;
}
.search {
  display: block;
  padding-right: 10px;
  margin-left: 90px;
  width: 25%;
  border: 1px solid black;
  box-sizing: border-box;
  color: #555;
  font-size: 20px;
  border-radius: 15px;
}
.margin-style{

}
.scroll{
  overflow-y: auto;
  height: 1100px;
  width: 700px;
}
.map{
  width: 100px !important;
  height: 700px !important;
  margin-right: 500px;
}


</style>