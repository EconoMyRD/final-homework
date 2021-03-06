var MakeGraphic = {
		
	connection: FactoryConnection.getConnection(),
		
	getDataForGraphic: function(dateS, dateE, cat) {
		
		$.ajax({
			url: MakeGraphic.connection + '/resources/transaction/relatory/',
			data:{'dateStart': dateS , 'dateEnd': dateE, 'category': cat, 'userId': sessionStorage.getItem('userId')},
			//method: 'GET',
			type: 'GET',
			contentType: 'application/json',
			//dataType: "json",
			success: function(jsonString){
				
				var json = JSON.parse(jsonString);
				var jsonGraphic = JSON.parse(json[0]);
				var jsonTable = JSON.parse(json[1]);
			    MakeGraphic.drawChart(jsonGraphic);
			    MakeGraphic.createTable(jsonTable);
			}
		});
	},
	
	 getData: function(json){
        var list = [];
        for(var i =0;i<Object.keys(json).length; i++){
            list.push(json[i].value);
           
        }
      //  alert('data : ' +list.toString());
       return list; 
       
    },
    
    
     getCategory: function(json){
        var list = [];
        for(var i =0;i<Object.keys(json).length; i++){
            list.push(json[i].name);
           //alert(json[i].name);
        }
       // alert('categories: ' + list.toString());
        return list; 
    },
	
    
    
    drawChart: function(json) {    
        // Create the chart
        $('#chart_div').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Relatório de Gastos'
            },
            subtitle: {
                text: 'Clique nas colunas para ver detalhes'
            },
            xAxis: {
                type: 'category',
                categories : MakeGraphic.getCategory(json)
            },
            yAxis: {
                title: {
                    text: 'Gasto Total'
                }

            },
            legend: {
                enabled: false
            },
            plotOptions: {
                series: {
                    cursor: 'pointer',
                    point: {
                        events: {
                            click: function () {
                                MakeGraphic.getDataForDetailedGraphic(this.category);
                            }
                        }
                    }
                }
            },

            tooltip: {
                headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                pointFormat: '<span style="color:{point.color}">{point.name}</span> <b>R$ {point.y:.2f}</b><br/>'
            },

            series: [{
                name: "Valor",
                colorByPoint: true,
                data: MakeGraphic.getData(json)
            }],


            });
    },
    
    



    drawDetailedChart: function(json) {
       
        $('#chart_div').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Relatório de Gastos Detalhados: ' + MakeGraphic.getSubcategory(json)
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                type: 'category',
                categories : MakeGraphic.getDates(json)
            },
            yAxis: {
                title: {
                    text: 'Gasto Total'
                }

            },
            legend: {
                enabled: false
            },

            tooltip: {
                headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                pointFormat: '<span style="color:{point.color}">{point.name}</span> <b>R$ {point.y:.2f}</b><br/>'
            },

            series: [{
                name: "Valor",
                colorByPoint: true,
                data: MakeGraphic.getData(json)
            }],


          });
    },

    
    getSubcategory: function(json) {
		//alert(JSON.stringify(json));
    	//if(json.hasOwnProperty('subcategory'))
    		return json[0].subcategory;
    	//else
    	//	return json[0].category
	},
	

    getDates: function(json) {
    	var list = [];
        for(var i =0;i<Object.keys(json).length; i++){
            list.push(MakeGraphic.formatDate(json[i].date));
        }
        return list; 
	},
	
	
    getDataForDetailedGraphic: function(subcategory) {
//    	if($("#category option:selected").text() == "Geral"){
    		var dateS = new Date();
            var dateE = new Date();
            dateS = $('#dateStart').val();
            dateE = $('#dateEnd').val();
            
            var dateStart = Relatorio.formatDate(dateS);
            var dateEnd =  Relatorio.formatDate(dateE);
//            MakeGraphic.getDataForGraphic(dateStart, dateEnd, subcategory);
//    	}
            var flag =0;
    	$.ajax({
    		url: MakeGraphic.connection + '/resources/category',
    		success:function(json){
    			for(var i =0;i<Object.keys(json).length; i++){
    				if(json[i].nome == subcategory){
    					flag=1;
    					break;
    				}
    			}
    			if(flag){
    				MakeGraphic.getDataForGraphic(dateStart, dateEnd, subcategory);
    			}
    			else{
//    				var dateS = new Date();
//    	    		var dateE = new Date();
//    	    		dateS = $('#dateStart').val();
//    	    		dateE = $('#dateEnd').val();
//    	    		
//    	    		var dateStart = Relatorio.formatDate(dateS);
//    	    		var dateEnd =  Relatorio.formatDate(dateE);
//    	    		
    	    		$.ajax({
    	    			url : MakeGraphic.connection + '/resources/transaction/relatory/detailed',
    	    			data: {'dateStart' : dateStart , 'dateEnd' : dateEnd, 'subcategory' : subcategory, 'userId': sessionStorage.getItem('userId')},
    	    			method: 'GET',
    	    			
    	    			success: function(jsonString) {
    	    				var json = JSON.parse(jsonString);
    	    				
    	    				var jsonGraphic = JSON.parse(json[0]);
    	    				var jsonTable = JSON.parse(json[1]);
    	    				
    	    				MakeGraphic.drawDetailedChart(jsonGraphic);
    	    				MakeGraphic.createTable(jsonTable);
    	    			}
    	    		});
    			}
    		}
    	
    	});
    	
    },

    
     formatDate: function(input){
        var p = input.split(/\D/g);
        var result = [p[2],p[1],p[0]].join("/");   

        return result;
    },    
    
    
    getDataForTable: function(json, total) {    	
    	var records = [];
        for (var j in json) {
            records.push({recid: json[j].id, categoria: json[j].nameCat, descricao: json[j].description , valor: json[j].value, data: new Date(json[j].date).toLocaleString().split(" ")[0] });
        }
        records.push({summary: true, recid: '', categoria: '<span style="float: right;font-size: 20px;">Saldo</span>', descricao: '<span style="float: left;font-size: 20px;">R$ ' + total + '</span>'});
       return records;
	},
	
	
	total: function(callback){
    	$.ajax({
    		url : Relatorio.connection + '/resources/user/sale/' + sessionStorage.getItem('userId'),
    		method:'GET',
    		success: function(total) {
    			callback(total);
    		}
		});
    },
	
    createTable: function(json) {
    	var callback = function(total){
    		MakeGraphic.drawTable(json,total);
    	};
    	
    	MakeGraphic.total(callback);
    	
    },
    
    drawTable: function(json, total){
    	var data = MakeGraphic.getDataForTable(json);
    	console.log(data);
	    $(function () {    
	        $('#table').w2grid({ 
	            name: 'table', 
	            show: { 
	                toolbar: true,
	                footer: true,
	            },
	            searches: [                
	                { field: 'categoria', caption: 'categoria', type: 'text' },
	                { field: 'descricao', caption: 'descricão', type: 'text' },
	                { field: 'data', caption: 'data', type: 'date' },
	            ],
	            columns: [                
	               // { field: 'recid', caption: 'ID', size: '5%', sortable: true, attr: 'align=center' },
	                { field: 'categoria', caption: 'categoria', size: '20%', sortable: true },
	                { field: 'descricao', caption: 'descricão', size: '35%', sortable: true, /*editable: { type: 'text' } */},
	                { field: 'valor', caption: 'valor', size: '20%', sortable: true, render: 'money' ,/* editable: { type: 'float' }*/style: 'text-align: center'},
	                { field: 'data', caption: 'data', size: '20%', sortable: true , /*editable: { type: 'date' }*/},
	            ],
	           
	            records: [ ],
	            
	            toolbar: {
	                items: [
	                    { id: 'save', type: 'button', caption: 'Salvar', icon: 'w2ui-icon-plus' }
	                ],
	                onClick: function (event) {
	                    if (event.target == 'save') {
	                    	MakeGraphic.updateTransaction();
	                        //alert(w2ui['table'].getChanges().recid)
	                    }
	                }
	            },
	        });
	        
	        w2ui['table'].clear();
	        w2ui['table'].add(MakeGraphic.getDataForTable(json, total));
	        
	    });
    },
    
    updateTransaction: function(){
    	var update = function(json){
    		var changes = w2ui['table'].getChanges()[0];
    		json.valor = changes.valor;
    		json.descricao = changes.descricao;
    		json.data = changes.data;
    		
    		console.log(json);
    		$.ajax({
    			url: MakeGraphic.connection + '/resources/transaction/update/' + changes.recid,
    			method: 'PUT',
    			data: json,
    			
    			success: function(){
    				alert('ok');
    			}
    		});
    	};
    	MakeGraphic.getById(update);
    	
    },
    
    
    getById: function(callback){
    	$.ajax({
    		url: MakeGraphic.connection + '/resources/transaction/get/' + w2ui['table'].getChanges()[0].recid,
    		method:'GET',
    		
    		success: function(transaction){
    			var json = JSON.parse(transaction);
    			callback(json);
    		}
    	});
    }
    
};
