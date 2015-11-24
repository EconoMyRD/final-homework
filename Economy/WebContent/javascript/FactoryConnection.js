var FactoryConnection = {
	localhost: true,

		getConnection: function(){
			if(FactoryConnection.localhost){
				return "http://localhost:8080/Economy"
			}
			else{
				// return the web conection
				return "http://http://economybr.cf/";
			}
		}
}