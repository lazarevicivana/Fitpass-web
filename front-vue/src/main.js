import "bootstrap/dist/css/bootstrap.css"
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import OpenLayersMap from 'vue3-openlayers'
import Notifications from '@kyvg/vue3-notification'
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faHouse , faUser,faUsers,faUserPlus, faDumbbell,faPlus, faPercent,faQuestion,faCreditCard,faMagnifyingGlass,faThumbsDown,faThumbsUp,faXmark, faBox,faPenClip} from '@fortawesome/free-solid-svg-icons'
library.add(faHouse,faUser,faUsers,faUserPlus,faDumbbell,faPlus,faPercent,faQuestion,faCreditCard,faMagnifyingGlass,faThumbsDown,faThumbsUp,faXmark,faBox,faPenClip)
createApp(App).use(router)
              .use(OpenLayersMap)
              .use(Notifications)
              .component('font-awesome-icon', FontAwesomeIcon)
              .mount('#app')

