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
			this.user = { id: null, name: null, surname: null, username: null, password: null, birthday: null, gender: Gender.MALE,userRole:UserRole.CUSTOMER,deleted:false,banned:false };
		},
		createOrEditProduct: function(event) {
			//this.error = "";
			/*if (this.selectedProduct.polaziste.toLowerCase() === this.selectedProduct.odrediste.toLowerCase()) {
				this.error = "NE MOZE ISTI NAZIV ZA POLAZISTE I ODREDISTE";
				alert("NE MOZE ISTI NAZIV ZA POLAZISTE I ODREDISTE");
				event.preventDefault();*/
				//return;
				//this.mode = "CREATE";
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