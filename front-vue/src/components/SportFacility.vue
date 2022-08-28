<template>
  <div class="container">
  <div class="sportFacility">
    <div class="row">
      <div class="col-sm">
    <table class="td-style">
      <tr><td style="font-size: 30px">{{sportFacility.name}}</td></tr>
      <tr><td> Location : {{sportFacility.location}}</td></tr>
      <tr><td> Status : {{convertStatus(sportFacility)}}</td></tr>
      <tr><td>Type: {{sportFacility.type}}</td></tr>
      <tr><td> Average grade: {{sportFacility.averageGrade}}</td></tr>
      <tr><td>Open time: {{dateTime(sportFacility.openTime)}}</td></tr>
      <tr><td> Close time: {{dateTime(sportFacility.closeTime)}}</td></tr>
    </table>
      </div>
      <div class="col">
        <img  :src="getImgUrl(sportFacility.name)" :alt="sportFacility.name" class="ico size border border-white"/>
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
      if (sportFacility.worikng === true)
        return "Open";
      else
        return "Closed";
    },
    dateTime(value) {
      return moment(value).format('hh:mm');
    },
    getImgUrl(facility){
      console.log(facility)
      if(facility === ''){
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
