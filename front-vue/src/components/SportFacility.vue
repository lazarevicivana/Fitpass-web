<template>
  <div class="container">
  <div class="sportFacility">
    <div class="row">
      <div class="col-sm">
    <table>
      <tr><td>{{sportFacility.name}}</td></tr>
      <tr><td> Location : {{sportFacility.location}}</td></tr>
      <tr><td> Status : {{convertStatus(sportFacility)}}</td></tr>
      <tr><td>Type: {{sportFacility.type}}</td></tr>
      <tr><td> Average grade: {{sportFacility.averageGrade}}</td></tr>
      <tr><td>Open time: {{dateTime(sportFacility.openTime)}}</td></tr>
      <tr><td> Close time: {{dateTime(sportFacility.closeTime)}}</td></tr>
    </table>
      </div>
      <div class="col">
        <img  :src="getImgUrl(sportFacility.name)" :alt="sportFacility.name" class="ico size"/>
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
     // console.log(typeof(sportFacility.isWorking))
      if (sportFacility.worikng === true)
        return "Open";
      else
        return "Closed";
    },
    dateTime(value) {
      return moment(value).format('MM/DD/YYYY hh:mm');
    },
    getImgUrl(facility){
      let images = require.context('../assets/', false, /\.png$/);
      return images('./' + facility + ".png")

    }

  }/*,
  data(){
    return{
      sportFacility: null
    }
  }*/
}
import moment from "moment";
</script>

<style scoped>
.size{
  width: 200px;
  height: 200px;
}

</style>
