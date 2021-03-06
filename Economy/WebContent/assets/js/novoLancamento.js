var novoLancamento = {
    
	connection: FactoryConnection.getConnection(),
	
    init: function(){
        novoLancamento.setForm();
    },
    
    setForm: function(){
    	//novoLancamento.getCategories();
       $('#category').on('load',novoLancamento.getCategories());
        $('#category').change(novoLancamento.changeSubcategory);
        $('#submit').on('click', novoLancamento.getValues);
    },

    
    changeSubcategory: function(){
        var category = novoLancamento.getOptionCategory();
        novoLancamento.getSubcategories(category);
    },
    
    
    getOptionCategory: function(){
        var category =document.getElementById('category');
        var selected = String(category.options[category.selectedIndex].value);
        
        return selected;
    },                            



     getSubcategories: function(select){    
          $.ajax({
        	  url: novoLancamento.connection + '/resources/category/sub/' + select,
        	 // data: {'select': select},
        	  method:'GET',
        	  
        	  success: function(json) {
        		  var field = document.getElementById('subcategory');
        		  var html = novoLancamento.getHTML(JSON.stringify(json));
        		  novoLancamento.showHTML(html, field);
        	  }
          });
     },
        	  

    
    getValues: function(){
        var description = $('#description').val();
        var value = $('#value').val();
        var subcategory  = document.getElementById('subcategory');
        var selectedSub = String(subcategory.options[subcategory.selectedIndex].value);
        var date = String($('#date_transaction').val());  
        var category = document.getElementById('category');
        var selectedCategory = String(category.options[category.selectedIndex].value);
        formatedDate = novoLancamento.formatDate(date);
        
        novoLancamento.saveOnDataBase(description,value,selectedSub,formatedDate,selectedCategory);

    },

    //format date (dd/mm/yyyy)  for send to backend
    formatDate: function(input){
        var p = input.split(/\D/g);
        var result = [p[2],p[1],p[0]].join("/");   

        return result;
    },

    saveOnDataBase: function(description,value, subcategory,date, selectedCategory){
        var data  = {
    			description : description ,
    			value : value , 
    			subcategory : subcategory ,
    			date_transcation : date ,
    			category : selectedCategory,
    			user : sessionStorage.getItem('userId')
    		}; 	
    	$.ajax({
        	url:  novoLancamento.connection +'/resources/transaction',
        	type: 'POST',        	
        	contentType: 'application/json',
        	dataType: "json",
        	data: JSON.stringify(data)
        });
      },
  

    getHTML: function(json){
    	var options = JSON.parse(json);
        var html= "";

        for (var i in options) {
            html+= '<option value = "';
            html+= options[i].id + '">';
            html+= options[i].nome;
            html += '</option>';  
        }
        return html;
    },
    
    
    showHTML: function(html, field){
        field.innerHTML = html;
    },
  
	
	
	getCategories: function(){
		
		$.ajax({
			url: novoLancamento.connection + '/resources/category',
			method: 'GET',
			success: function(json) {					            
	            var field = document.getElementById('category');
	            var html = novoLancamento.getHTML(JSON.stringify(json));
	            novoLancamento.showHTML(html, field);
	            novoLancamento.changeSubcategory();
			}
		})
    
	}
};


novoLancamento.init();





