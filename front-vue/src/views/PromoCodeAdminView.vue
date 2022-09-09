<template>
<div class="container">
  <div class="row">
    <h1>Promo codes</h1>
  </div>
  <div class="row padding-style">
    <button class="btn btn-primary button-basic" @click="onAdd()">Add promo code</button>
  </div>
  <div class="row row-style gy-4 row-cols-4 align-items-center" >
  <div v-for="promoCode in promoCodes">
    <div class="col-lg-8 colorDiv">
      <PromoCode :promoCode="promoCode"></PromoCode>      </div>
  </div>
</div>
</div>
</template>

<script>
import PromoCode from "@/components/PromoCode";
import axios from "axios";
export default {
  name: "PromoCodeAdminView",
  components: {PromoCode},
  data(){
    return {
      promoCodes: {}
    }
  },
  created(){
    this.getAll()
  },
  methods:{
    getAll(){
      axios.get('http://localhost:8080/FitnessCenter/rest/promo-codes/')
          .then(
              response => {
                this.promoCodes = response.data
              }
          )
    },
    onAdd(){
      this.$router.push('/add-promo-code');
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
  width: 105%;
}

.padding-style{
  margin-bottom: 30px;
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

.button-basic{
  color: white;
  margin-top: 40px;
  margin-left: 20px;
  display: block;
  padding-right: 10px;
  font-size: 24px;
  width: 20%;
  vertical-align: center;
  box-sizing: border-box;
  border: none;
  position: relative;
  -ms-transform: translateX(-50%);
  transform: translateX(25%);
}
.button-basic{
  margin-top: 40px;
}
h1{
  font-size: 60px;
  font-weight: bolder;;
}

</style>