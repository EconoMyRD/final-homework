var Relatorio = {

	connection: FactoryConnection.getConnection(),
		
    init: function(){
        document.getElementById('submit').addEventListener('click', Relatorio.getRelatory);
        document.getElementById('category').addEventListener('load', Relatorio.getCategories());
        
    },
    
    
    total: function(){
    	$.ajax({
    		url : Relatorio.connection + '/resources/sale',
    		method:'GET',
    		success: function() {
    			var total = ajax.responseText;
    			Relatorio.showTotal(total);
    		}
		});
    },
    
    
    showTotal: function(total){
    	var field = $('#total');
    	field.innerHTML = 'Saldo : ' + total;
    },
    
   
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
        Relatorio.total();
    },

    
    formatDate: function(input){
        var p = input.split(/\D/g);
        var result = [p[2],p[1],p[0]].join("/");   

        return result;
    },
    
    
    showOptions: function(json, field){
        var options = JSON.parse(json);
        var html= "";

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
	},
    
	    
		showOptions: function(json, field){
			var options = JSON.parse(json);
			var html= "";
			
			for (var i in options) {
				html+= '<option value = "';
				html+= options[i].id + '">';
				html+= options[i].nome;
				html += '</option>';   				    				
			}    		
			field.innerHTML = html;
		}
    
	
	
};

Relatorio.init();
