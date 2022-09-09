<template>
<div class="container">
<h1 class="padding-style title">Welcome</h1>
  <div class="row padding-style">
    <div class="col">
      <input type="text"  v-model="search" @change="filterFacilities" placeholder="search">
    </div>
    <div class="col">
      <select v-model="filter" @change="filterFacilities">
        <option>All</option>
        <option>Open</option>
        <option>Closed</option>
        <option>POOL</option>
        <option>SPORTCENTER</option>
        <option>DANCINGSTUDIO</option>
        <option>GYM</option>
        <option>BOWLINGCENTER</option>
        <option>SHOOTINGRANGE</option>
      </select>
    </div>
    <div class="col">
      <select v-model="sort" @change="FilterUsers">
        <option>Sort</option>
        <option>Name asc</option>
        <option>Name desc</option>
        <option>Average grade asc</option>
        <option>Average grade desc</option>
        <option>City asc</option>
        <option>City desc</option>
      </select>
    </div>
  </div>
  <div class="row">
    <div class="col-4 scroll">
      <div class="row  row-style text-center" v-for="sportFacility in this.filterFacilities()">
        <div  class="col colorDiv hover scroll-row"  >
          <SportFacility @click="FacilitieDetail(sportFacility)" :sportFacility="sportFacility"/>
        </div>
      </div>
    </div>
    <div class="col">
      <Map :facilities="filterFacilities()"></Map>
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
      search : '',
      filter: 'All',
      filteredFacilities:[],
      sort: 'Sort'
    }
  },
  created() {
    this.getAll()
  },
  computed:{

  },
  methods:
  {
     getAll(){
      axios.get('http://localhost:8080/FitnessCenter/rest/facilities/get-all-dto')
          .then(response =>{
            this.sportFacilities = response.data
            this.filteredFacilities = this.sportFacilities
            console.log(this.filteredFacilities)
          })
     },
    FacilitieDetail(sportFacility)
    {
      this.$router.push({name:"facilitiesDetail",params:{data:JSON.stringify(sportFacility)}})
    },
    searchFacilities(){
        const filterNotNullAndUndefined = (sportFacilities) => (sportFacilities?.name !== null && sportFacilities?.name !== undefined
                                                                && sportFacilities?.type !== null && sportFacilities?.type !== undefined
                                                                && sportFacilities?.averageGrade !== null && sportFacilities?.averageGrade !== undefined
                                                                && sportFacilities?.street !== null && sportFacilities?.street !== undefined)
        this.sportFacilities.filter(filterNotNullAndUndefined)

       this.filteredFacilities = [... this.sportFacilities.filter(sportFacility => this.search.toLowerCase().split(' ').every(s => sportFacility.name.toLowerCase().includes(s))
          || this.search.toLowerCase().split(' ').every(s => sportFacility.type.toLowerCase().includes(s))
          || this.search.toLowerCase().split(' ').every(s => sportFacility.averageGrade.toLowerCase().includes(s))
          || this.search.toLowerCase().split(' ').every(s => sportFacility.street.toLowerCase().includes(s))
           || this.search.toLowerCase().split(' ').every(s => sportFacility.city.toLowerCase().includes(s)))]
    },
    filterFacilities() {
       this.searchFacilities()
       if(this.filter !== 'All'){
         if(this.filter === 'Open'){
           this.filteredFacilities = [...this.filteredFacilities.filter(facility =>  facility.isWorking)]
         }else if(this.filter === 'Closed'){
           this.filteredFacilities = [...this.filteredFacilities.filter(facility =>  !facility.isWorking)]
         }else{
           this.filteredFacilities = [...this.filteredFacilities.filter(facility =>  facility.type.toLowerCase().includes(this.filter.toLowerCase()))]

         }
       }
       this.sortFacilities()
        return this.filteredFacilities
    },
    sortFacilities(){
        if(this.sort === 'Name asc'){
          this.filteredFacilities.sort((a,b) => (a.name.toLowerCase() < b.name.toLowerCase()) ? -1 : 1)
        }else if(this.sort === 'Name desc'){
          this.filteredFacilities.sort((a,b) => (a.name.toLowerCase() < b.name.toLowerCase()) ? 1 : -1)
        }
        else if(this.sort === 'Average grade desc'){
          this.filteredFacilities.sort((a,b) => (a.averageGrade.toLowerCase() < b.averageGrade.toLowerCase()) ? 1 : -1)
        }
        else if(this.sort === 'Average grade asc'){
          this.filteredFacilities.sort((a,b) => (a.averageGrade.toLowerCase() < b.averageGrade.toLowerCase()) ? -1 : 1)
        }
        else if(this.sort === 'City asc'){
          this.filteredFacilities.sort((a,b) => (a.city.toLowerCase() < b.city.toLowerCase()) ? -1 : 1)

        } else if(this.sort === 'City desc') {
          this.filteredFacilities.sort((a, b) => (a.city.toLowerCase() < b.city.toLowerCase()) ? 1 : -1)
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
  padding: 20px;
  color: white;
  width: 50%;
}
.scroll-row{
  margin-right: 80px;
}
.map-row{
  margin-left: 60px;
}
.padding-style{
  margin-bottom: 80px;
}
.row-style{
  margin-bottom: 60px;
}
.title{
  font-weight: bolder;
  font-size: 80px;
}
.scroll{
  overflow-y: auto;
  height: 1100px;
  width: 580px;
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
.hover{
  transition: transform .2s;
  cursor: pointer;
}
.hover:hover{
  transform: scale(1.1);
}

</style>