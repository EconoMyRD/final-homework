var GetOptions = {
    
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
            novoLancamento.changeSubcategory();
    },
      
    showOptionsSubcategory: function(json, field){
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

    getAllCategories: function(){
        var ajax = ajaxInit();
        var url = 'http://localhost:8080/Economy/ServletCategory';
        ajax.open('GET', url, true);
        ajax.send();

        ajax.onreadystatechange = function(){
            if (ajax.readyState==4 && ajax.status==200){
                var json = ajax.responseText;
                var field = document.getElementById('category');
                GetOptions.showOptions(json,field);
            }
        };
        
    }
    
};