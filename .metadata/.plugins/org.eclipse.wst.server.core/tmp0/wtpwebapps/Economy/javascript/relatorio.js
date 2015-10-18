var Relatorio = {

    init: function(){
        document.getElementById('submit').addEventListener('click', Relatorio.getRelatory);
        document.getElementById('category').addEventListener('load', Relatorio.getCategories());
        
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
	    var ajax = ajaxInit();
	    var url = 'http://www.economy.zz.mu/ServletCategory';
	    ajax.open('GET', url, true);
	    ajax.send();
	
	    ajax.onreadystatechange = function(){
	        if (ajax.readyState==4 && ajax.status==200){
	            var json = ajax.responseText;
	            var field = document.getElementById('category');
	            Relatorio.showOptions(json,field);
	        }
	    };
	    
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
