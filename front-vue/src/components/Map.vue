<template>
  <ol-map :loadTilesWhileAnimating="true" :loadTilesWhileInteracting="true" style="height: 100%">

    <ol-view ref="view" :center="center" :rotation="rotation" :zoom="zoom"
             :projection="projection" />

    <ol-tile-layer>
      <ol-source-osm />
    </ol-tile-layer>

    <ol-vector-layer>

      <ol-source-vector :format="geoJson" :projection="projection">

        <ol-feature v-for="obj in this.facilities" v-bind:key="obj.sportFacilityId">

          <ol-geom-point :coordinates="[obj.longitude, obj.latitude]"></ol-geom-point>
          <ol-style>
            <ol-style-icon :src="markerIcon" :scale="0.5"></ol-style-icon>
          </ol-style>
        </ol-feature>

      </ol-source-vector>
    </ol-vector-layer>

  </ol-map>
</template>


<script>
import {ref, inject} from 'vue'
import markerIcon from '../assets/point.png'

import 'ol/ol.css'
import axios from 'axios'
export default {
  setup() {
    const center = ref([19.848, 45.251])
    const sumthing = ref([19.803,45])
    const projection = ref('EPSG:4326')
    const zoom = ref(13.94)
    const rotation = ref(0)
    const format = inject('ol-format')
    const geoJson = new format.GeoJSON();
    return {
      center,
      projection,
      zoom,
      rotation,
      geoJson,
      markerIcon,
      sumthing
    }
  },
  props:{facilities: Array},
  // data(){
  //   return{
  //     facilities: []
  //   }
  // },
  created(){
    axios
        .get('http://localhost:8080/FitnessCenter/rest/facilities/GetAll')
        .then(response => {
        //  this.facilities = response.data
        })
  },

}
</script>


<style>
</style>