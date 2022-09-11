<template>
  <div class="home">
<!--    <img alt="Vue logo" src="../assets/logo.png">-->
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import HeaderView from "@/views/HeaderView";

export default {
  name: 'HomeView',
  components: {
    HeaderView,
    HelloWorld
  }
}
</script>
