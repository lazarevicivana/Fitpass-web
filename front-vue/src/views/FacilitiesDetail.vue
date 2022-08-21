<template>
    <h1 class="padding-style">Facility details</h1>
    <div  class="row gy-2 row-style colorDiv " >
      <div  class="container-sm d-flex align-items-center justify-content-center">
        <SportFacility :sportFacility="sportFacility"></SportFacility>
      </div>
    </div>
</template>

<script>
import SportFacilitieDetailPage from "@/components/SportFacilitieDetailPage";
import SportFacility from "@/components/SportFacility";
export default {
  name: "FacilitiesDetail",
  /*props:{ sportFacility: Object},*/
  data(){
    return{
     sportFacility:{
       sportFacilityId: '',
       name : '',
       location : '',
       isWorking : false,
       type : '',
       worikng:false,
       closeTime:'',
       openTime:'',
       averageGrade:'',
       facilityContent:[],

    }}
  },
  components: {SportFacility, SportFacilitieDetailPage},
  created() {
    console.log(this.sportFacility.name);
    this.sportFacility = JSON.parse(this.$route.params.data);
},methods: {
    convertStatus(sportFacility) {
      // console.log(typeof(sportFacility.isWorking))
      if (sportFacility.worikng === true)
        return "Open";
      else
        return "Closed";
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