import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Facilities from "@/views/Facilities";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/facilities',
    name: 'facilities',
    component: () => import(/* webpackChunkName: "facilities" */ '../views/Facilities.vue')
  }
  ,
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "facilities" */ '../views/LoginView.vue')
  }
  ,
  {
    path: '/register',
    name: 'register',
    component: () => import(/* webpackChunkName: "register" */ '../views/RegisteView.vue')
  },
  {
    path: '/facilitiesDetail',
    name: 'facilitiesDetail',
    component: () => import(/* webpackChunkName: "register" */ '../views/FacilitiesDetail.vue')
  },
  {
    path: '/create-manager',
    name: 'create-manager',
    component: () => import(/* webpackChunkName: "register" */ '../views/AddManagers.vue')
  },
  {
    path: '/create-trainer',
    name: 'create-trainer',
    component: () => import(/* webpackChunkName: "register" */ '../views/AddTrainerView.vue')
  },
  {
    path: '/all-users',
    name: 'all-users',
    component: () => import(/* webpackChunkName: "register" */ '../views/AllUsersView.vue')
  },
  {
    path: '/manager-facility',
    name: 'manager-facility',
    component: () => import(/* webpackChunkName: "register" */ '../views/ManagerFacilityView.vue')
  },
  {
    path: '/:id/add-training',
    name: ':id/add-training',
    component: () => import(/* webpackChunkName: "register" */ '../components/CreateTraining.vue')
  },
  {
    path: '/:id/add-content',
    name: ':id/add-content',
    component: () => import(/* webpackChunkName: "register" */ '../components/CreateContent.vue')
  }
  ,
  {
    path: '/manager-trainings',
    name: 'manager-trainings',
    component: () => import(/* webpackChunkName: "register" */ '../views/AllTrainingsManagerView.vue')
  },
  {
    path: '/manager-trainers',
    name: 'manager-trainers',
    component: () => import(/* webpackChunkName: "register" */ '../views/ManagerTrainersView.vue')
  },
  {
    path: '/trainer-trainings',
    name: 'trainer-trainings',
    component: () => import('../views/AllTrainingsTrainerView.vue')
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import('../views/ProfileView.vue')
  },
  {
    path: '/manager-trainings/:id',
    name: 'manager-trainings/:id',
    component: () => import(/* webpackChunkName: "register" */ '../components/EditTraining.vue')
  },
  {
    path: '/add-facility',
    name: 'add-facility',
    component: () => import(/* webpackChunkName: "register" */ '../components/CreateSportFacility.vue')

  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
