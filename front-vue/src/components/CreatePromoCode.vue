<template>
  <form @submit.prevent="promoCodeSubmit">
    <h1>Add promo code</h1>
    <label>Code:</label>
    <input type="text" v-model="promoCode.code" required>
    <label>Expire date:</label>
    <input type="date" v-model="promoCode.expireDate" required>
    <label>Number of usage:</label>
    <input type="number" v-model="promoCode.numberOfUsage" required>
    <label>Discount percentage:</label>
    <input type="number" v-model="promoCode.percentage" required>
    <input  type="submit" class="btn btn-primary button-basic" value="Add"/>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "CreatePromoCode",
  data(){
    return{
      promoCode :{
        code: '',
        expireDate: Date,
        numberOfUsage: '',
        percentage: ''
      },
      promoCodes:[],
      code: true
    }
  },
  created() {
  this.getPromoCodes()
    },
  methods:{
    promoCodeSubmit(){
      this.code = true
      this.promoCodes.forEach(c =>{
        if(c.code === this.promoCode.code){
          this.code = false
          if (this.code === false) {
            this.$notify({
              title: 'Error while creating training',
              type: 'error',
              text: "A training with that name already exists in database!",
              closeOnClick: true
            })
            return;
          }
        }})
      if(this.code){
      axios.post('http://localhost:8080/FitnessCenter/rest/promo-codes/', this.promoCode)
          .then(
              result => {
                this.promoCode = result.data
                console.log(this.promoCode)
                this.$router.push('/success-promocode-create')
              }
          )}
    },
    getPromoCodes(){
      axios.get('http://localhost:8080/FitnessCenter/rest/promo-codes/')
          .then(
              result => {
                this.promoCodes = result.data
              }
          )

    }
  }
}
</script>

<style scoped>
form {
  max-width: 600px;
  margin: 80px auto 30px 300px;
  background: #072238;
  text-align: center;
  padding: 40px;
  border-radius: 10px;
  font-size: 18px;
  color: white;
}h1{
   font-weight: bolder;
   font-size: 50px;
 }
label {
  color: white;
  display: inline-block;
  text-align: left;
  margin: 25px 0 15px;
  font-size: 0.6em;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bolder;
  font-size: 18px;
}input,select {
   display: block;
   padding-left: 10px;
   padding-right: 10px;
   margin-left: 70px;
   width: 70%;
   box-sizing: border-box;
   border: none;
   border-bottom: 1px solid #ddd;
   color: #555;
   font-size: 18px;
   border-radius: 15px;

 }
.button-basic{
  color: white;
  margin-top: 40px;
  display: block;
  padding-left: 10px;
  padding-right: 10px;
  margin-left: 55px;
  font-size: 24px;
  width: 50%;
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
.button-padding{
  margin-top: 50px;
}
</style>