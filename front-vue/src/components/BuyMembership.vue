<template>
  <form @submit.prevent="onBuySubmit">
    <h1>Buy membership</h1>
    <label>Type:</label>
    <input type="text" v-model="membershipPackage.type">
    <label>Number of trainings:</label>
    <input type="text" v-model="membershipPackage.numberOfTrainings" >
    <label>Description:</label>
    <input type="text" v-model="membershipPackage.description" >
    <label>Promo code:</label>
    <input type="text" v-model="promoCode.code" >
    <input  type="submit" class="btn btn-primary button-basic" value="Buy"/>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "BuyMembership",
  props:{user : Object},
  data(){
    return {
      membership:{
        type: '',
        dateOfPayment: '',
        startDate: Date,
        endDate: Date,
        price: 0,
        customerId: '',
        isActive: true,
        numberOfTrainings: 0,
        usedTrainings : 0
      },
      membershipPackage :{
        numberOfTrainings: 0
      },
      promoCode : {
        code :'',
        percentage:0,
        exireDate: Date,
        numberOfUsage: 0
      }
    }
  },
  created() {

    axios.get('http://localhost:8080/FitnessCenter/rest/membership-package/'+ this.$route.params.id)
        .then(
            result => {
              this.membershipPackage = result.data
            }
        )
  },
  methods:{
    getPromoCode(){
      axios.get('http://localhost:8080/FitnessCenter/rest/promo-codes/' + this.promoCode.code )
          .then(
              result => {
                this.promoCode = result.data
                this.setMembership()
                this.deactivatePreviousMembership()
              }
          )
    },
    onBuySubmit(){
      this.getPromoCode()
    },
    deactivatePreviousMembership(){
      axios.put('http://localhost:8080/FitnessCenter/rest/memberships/' , this.membership)
          .then(
              result => {
                //this.membership = result.data
                this.createMembership()
              }
          )
    },

    createMembership(){
      axios.post('http://localhost:8080/FitnessCenter/rest/memberships/' , this.membership )
          .then(
              result => {
                this.membership = result.data
              }
          )
    },
    updatePromoCodeUsage(){
      --this.promoCode.numberOfUsage
      axios.put('http://localhost:8080/FitnessCenter/rest/promo-codes/' , this.promoCode )
          .then(
              result => {
                this.promoCode = result.data
              }
          )
    },
    checkNumberOfCodeUsage(){
      if(this.promoCode.numberOfUsage > 0){
        this.membership.price = this.discountCounter()
        this.updatePromoCodeUsage()
      }else {
        this.membership.price = this.membershipPackage.price
      }
    },
    checkExpireCodeDate(){
      const now = new Date()
      if( this.promoCode.exireDate > now)
      {
        this.checkNumberOfCodeUsage()
      }else {
        this.membership.price = this.membershipPackage.price
      }
    },
    setMembership(){
      this.checkExpireCodeDate()
      const endDate = new Date()
      this.membership.type = this.membershipPackage.type
      this.membership.customerId = this.user.username
      this.membership.numberOfTrainings = this.membershipPackage.numberOfTrainings
      console.log(this.membershipPackage.numberOfTrainings)
      this.membership.startDate =  new Date()
      this.membership.endDate = endDate
      if(this.membership.type === 'MONTH'){
        this.membership.endDate.setMonth(this.membership.endDate.getMonth() + 1)
      }else if(this.membership.type === 'THREE_MONTHS'){
        this.membership.endDate.setMonth(this.membership.endDate.getMonth() + 3)
      }
      else if(this.membership.type === 'SIX_MONTHS'){
        this.membership.endDate.setMonth(this.membership.endDate.getMonth() + 3)
      }
      else {
        this.membership.endDate.setFullYear(this.membership.endDate.setFullYear() + 1)

      }
    },
    discountCounter(){
      const discount = 1 - this.promoCode.percentage/100
       return  this.membershipPackage.price * discount
    }
  }
}
</script>

<style scoped>
form {
  max-width: 500px;
  margin: 30px auto;
  background: #072238;
  text-align: center;
  padding: 40px;
  border-radius: 10px;
  font-size: 15px;
  color: white;
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
  font-size: 15px;
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
   font-size: 20px;
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
</style>