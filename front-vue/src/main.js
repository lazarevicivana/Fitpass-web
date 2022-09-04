import "bootstrap/dist/css/bootstrap.css"
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import OpenLayersMap from 'vue3-openlayers'
import '@fortawesome/fontawesome'
createApp(App).use(router).use(OpenLayersMap).mount('#app')
import "bootstrap/dist/js/bootstrap"
