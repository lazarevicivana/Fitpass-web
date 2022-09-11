var sportFacilities = new Vue({
    el:'#sportsFacilities',
    data:{
        sportFacilities: null,
        title: "Sports Facilities",
        mode: "BROWSE",
		user: {}
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
		},
		showForm: function() {
			this.mode = "CREATE";
			this.user = { id: null, name: null, surname: null, username: null, password: null, birthday: null, gender:'FEMALE',userRole:'CUSTOMER',deleted:false,banned:false };
		},
		createOrEditProduct: function(event) {
				event.preventDefault();
				if (this.mode == "CREATE") {
				axios
					.post("rest/customers/Create", this.user)
					.then((response) => {
						alert("Registrovali ste se :)");
						this.mode = "BROWSE";
						this.sportFacilities.push(response.data);
					});
			}
			}
    }
});