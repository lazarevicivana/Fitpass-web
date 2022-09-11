const SportsFacilities = { template: '<sportsFacilities></sportsFacilities>' }
const Registration = {template: '<registration></registration>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: SportsFacilities},
	    { path: '/registration', component: Registration},	 
]
});

var app = new Vue({
	router,
	el: '#fitpass'
});