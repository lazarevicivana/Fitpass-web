<template>
  <div class="container">
  <div class="sportFacility">
    <div class="row">
      <div class="col-sm">
    <table class="td-style">
      <tr><td style="font-size: 30px">{{sportFacility.name}}</td></tr>
      <tr><td> Location : {{sportFacility.street }} {{sportFacility.number}}, {{sportFacility.city}}</td></tr>
      <tr><td> Status : {{convertStatus(sportFacility)}}</td></tr>
      <tr><td>Type: {{sportFacility.type}}</td></tr>
      <tr><td> Average grade: {{sportFacility.averageGrade}}</td></tr>
      <tr><td>Open time: {{sportFacility.openTime}}</td></tr>
      <tr><td> Close time: {{sportFacility.closeTime}}</td></tr>
    </table>
      </div>
      <div class="col">
        <img  :src="getImgUrl(sportFacility.sportFacilityId)" :alt="sportFacility.name" class="ico size border border-white"/>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: "SportFacility",
  props:{
    sportFacility : Object
  },
  methods:{
    convertStatus(sportFacility) {
      if (sportFacility.isWorking === true)
        return "Open";
      else
        return "Closed";
    },
    getImgUrl(facility){
      if(facility === '' || facility === null || facility === undefined){
        return
      }
      let images = require.context('../assets/', false, /\.png$/);
      return images('./' + facility + ".png")
    }

  }
}
import moment from "moment";
</script>

<style scoped>
.size{
  width: 200px;
  height: 200px;
}
.td-style{
  text-align: left;
  font-size: 17px;

}

</style>
