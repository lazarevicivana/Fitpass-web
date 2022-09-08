import "bootstrap/dist/css/bootstrap.css"
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import OpenLayersMap from 'vue3-openlayers'
import Notifications from '@kyvg/vue3-notification'
import '@fortawesome/fontawesome'
createApp(App).use(router).use(OpenLayersMap).use(Notifications).mount('#app')
import "bootstrap/dist/js/bootstrap"
