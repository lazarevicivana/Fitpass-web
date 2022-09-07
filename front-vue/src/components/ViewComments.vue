<template>
  <div class="row row-style gy-4 align-items-center" >
    <div v-for="comment in this.filterCommnets()">
      <div class="col colorDiv">
        <div class="row">
          <div class="col-8">
            <Comment :comment="comment" :user="user"></Comment>
          </div>
          <div class="col-4">
            <div class="row">
              <button class="button-basic" v-if="user.userRole === 'ADMIN' && comment.state === 'WAITING'" @click="onApprove(comment)" :comment="comment">Approve</button>
            </div>
            <div class="row">
              <button class="button-basic" v-if="user.userRole === 'ADMIN' && comment.state === 'WAITING'" @click="onDenie(comment)" :comment="comment">Denie</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Comment from "@/components/Comment";

export default {
  name: "ViewComments",
  components: {Comment},
  props:{
    facilityId: String,
    user: Object
  },
  data(){
    return{
      comments:[],
      filteredComments:[]
    }
  },
  created() {
    console.log(this.user)
    axios.get('http://localhost:8080/FitnessCenter/rest/comments/')
        .then(
            response => {
              this.comments = response.data
              this.filteredComments = this.comments
              console.log(this.filteredComments)
              console.log(this.comments)
            }
        )
  },
  methods:{
    filterCommnets(){
      this.filteredComments = this.comments
      if(this.user.userRole === 'CUSTOMER' || this.user.userRole === 'TRAINER'){
        this.filteredComments = [...this.filteredComments.filter(comment =>  comment.state === 'APPROVED')]
      }else if(this.user.userRole === 'MANAGER'){
        this.filteredComments = [...this.filteredComments.filter(comment =>  comment.state !== 'WAITING')]

      }
      return this.filteredComments
    },
    onApprove(comment) {
      comment.state = 'APPROVED'
      axios.put('http://localhost:8080/FitnessCenter/rest/comments/', comment)
          .then(
              response => {
                this.comment = response.data
              }
          )
    },
    onDenie(comment){
      comment.state = 'DENIED'
      axios.put('http://localhost:8080/FitnessCenter/rest/comments/', comment)
          .then(
              response => {
                this.comment = response.data
              }
          )
    }
  }
}
</script>

<style scoped>
.colorDiv{
  background: #BBBBBB;
  border-radius: 25px;
  border: 2px solid;
  width: 100%;
  padding: 20px;
  color: white;
}
.margin-style{
  margin-left: 100px;
}
.padding-style{
  margin-bottom: 80px;
}
.row-style{
  padding-bottom: 100px;
  margin-bottom: 30px;
}

.button-basic{
  background: #2c3e50;
  color: white;
  border-radius: 10px;
  width: 50%;
  height: 100%;
  margin-top: 20px;
  margin-bottom: 20px;
  margin-left: 140px;
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
</style>