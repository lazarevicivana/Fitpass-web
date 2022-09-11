var registration = new Vue({
    el:'#registration',
    data:{
        sportFacilities: null,
        title: "Registration"
    },
    mounted(){
        axios.get('rest/facilities/GetAll')
             .then(response =>(this.sportFacilities = response.data))
    },
    methods:{
		convertStatus: function(sf) {
			
			if(sf.isWorking===true)
				return "Radi";
				else
				return "Ne radi";
		}
    }
});