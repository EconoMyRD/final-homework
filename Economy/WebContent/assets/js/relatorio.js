var Relatorio = {

	connection: FactoryConnection.getConnection(),
		
    init: function(){
        document.getElementById('submit').addEventListener('click', Relatorio.getRelatory);
        document.getElementById('category').addEventListener('load', Relatorio.getCategories());
        
    },
    
    
//    total: function(){
//    	$.ajax({
//    		url : Relatorio.connection + '/resources/user/sale/' + sessionStorage.getItem('userId'),
//    		method:'GET',
//    		success: function(total) {
//    			Relatorio.showTotal(total);
//    		}
//		});
//    },
//    
//    
//    showTotal: function(total){
//    	var field = document.getElementById('total');
//    	field.innerHTML = 'Saldo : R$ ' + total;
//    },
    
   
    getRelatory: function(event) {
        event.preventDefault();
        var dateS = new Date();
        var dateE = new Date();
        dateS = document.getElementById('dateStart').value;
        dateE = document.getElementById('dateEnd').value;
        var category = document.getElementById('category').value;
        
        var dateStart = Relatorio.formatDate(dateS);
        var dateEnd =  Relatorio.formatDate(dateE);

        MakeGraphic.getDataForGraphic(dateStart, dateEnd, category);
       // Relatorio.total();
    },

    
    formatDate: function(input){
        var p = input.split(/\D/g);
        var result = [p[2],p[1],p[0]].join("/");   

        return result;
    },
    
    
    showOptions: function(json, field){
        var options = JSON.parse(json);
        var html= '<option value="0" >Geral</option>';

        for (var i in options) {
            html+= '<option value = "';
            html+= options[i].id + '">';
            html+= options[i].nome;
            html += '</option>';   				    				
        }    		
        field.innerHTML = html;
        
    },
       
	getCategories: function(){
	    $.ajax({
			url: Relatorio.connection + '/resources/category',
			method: 'GET',
			success: function(json) {			
				
				var field = document.getElementById('category');
	            Relatorio.showOptions(JSON.stringify(json),field);
			}
		})
	}
    
	    
		
    
	
	
};

Relatorio.init();
