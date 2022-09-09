import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Facilities from "@/views/Facilities";

const routes = [
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/',
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
  ,
  {
    path: '/:id/buy-training',
    name: '/:id/buy-training',
    component: () => import(/* webpackChunkName: "register" */ '../components/BuyTrainingComponent.vue')
  },
  {
    path: '/memberships',
    name: '/memberships',
    component: () => import(/* webpackChunkName: "register" */ '../views/MembershipsView.vue')
  },
  {
    path: '/promo-codes',
    name: '/promo-codes',
    component: () => import(/* webpackChunkName: "register" */ '../views/PromoCodeAdminView.vue')
  }
  ,
  {
    path: '/add-promo-code',
    name: '/add-promo-code',
    component: () => import(/* webpackChunkName: "register" */ '../components/CreatePromoCode.vue')
  },
  {
    path: '/:id/buy-membership',
    name: '/:id/buy-membership',
    component: () => import(/* webpackChunkName: "register" */ '../components/BuyMembership.vue')
  },
  {
    path: '/:id/comment',
    name: '/:id/comment',
    component: () => import(/* webpackChunkName: "register" */ '../components/LeaveAComment.vue')
  },
  {
    path: '/customer-trainings',
    name: 'customer-trainings',
    component: () => import('../views/AllTrainingsCustomerView.vue')
  },
  {
    path: '/success-profile-edit',
    name: 'success-profile-edit',
    component: () => import('../views/SuccessEditProfileView.vue')
  },
  {
    path: '/success-facility-create',
    name: 'success-facility-create',
    component: () => import('../views/SuccessFacilityView')
  },
  {
    path: '/success-manager-create',
    name: 'success-manager-create',
    component: () => import('../views/SuccessManagerView')
  },
  {
    path: '/success-trainer-create',
    name: 'success-trainer-create',
    component: () => import('../views/SuccessTrainerView')
  },
  {
    path: '/success-promocode-create',
    name: 'success-promocode-create',
    component: () => import('../views/SuccessPromoCodeView')
  },
  {
    path: '/success-training-create',
    name: 'success-training-create',
    component: () => import('../views/SuccessTrainingView')
  },
  {
    path: '/success-content-create',
    name: 'success-content-create',
    component: () => import('../views/SuccessContentView')
  },
  {
    path: '/success-payment',
    name: 'success-payment',
    component: () => import('../views/SuccessPaymentView')
  },
  {
    path: '/success-training-edit',
    name: 'success-training-edit',
    component: () => import('../views/SuccessEditTrView')
  },
  {
    path: '/success-comment',
    name: 'success-comment',
    component: () => import('../views/SuccessCommentView')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
