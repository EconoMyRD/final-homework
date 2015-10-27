var MakeGraphic = {
		
		
		connection: FactoryConnection.getConnection(),
		
		getDataForGraphic: function(dateS, dateE, cat) {
		$.ajax({
			url: MakeGraphic.connection + '/ServletRelatory',
			data:{ dateStart: dateS , dateEnd: dateE, category: cat},
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
    	alert(json);
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
                               // alert('Category: ' + this.category + ', value: ' + this.y);
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
		return json[0].subcategory;
	},
	

    getDates: function(json) {
    	var list = [];
        for(var i =0;i<Object.keys(json).length; i++){
            list.push(MakeGraphic.formatDate(json[i].date));
        }
        return list; 
	},
	
	
    getDataForDetailedGraphic: function(subcategory) {
    	var dateS = new Date();
        var dateE = new Date();
        dateS = $('#dateStart').val();
        dateE = $('#dateEnd').val();
        
        var dateStart = Relatorio.formatDate(dateS);
        var dateEnd =  Relatorio.formatDate(dateE);

    	
        var ajax = ajaxInit();
        if (ajax) {
            var url = FactoryConnection.getConnection() + '/ServletDetailedGraphic?dateStart='
                    + dateStart + '&dateEnd=' + dateEnd + '&subcategory=' + subcategory;
            ajax.open('GET', url, true);
            ajax.send();
        }
        ajax.onreadystatechange = function() {
            if (ajax.readyState == 4 && ajax.status == 200) {
                var jsonString = ajax.responseText;
                var json = JSON.parse(jsonString);
                MakeGraphic.drawDetailedChart(json);
                w2ui['table'].refresh();
                MakeGraphic.createTable(json);
            }
        };
    },

    
     formatDate: function(input){
        var p = input.split(/\D/g);
        var result = [p[2],p[1],p[0]].join("/");   

        return result;
    },    
    
    
    getDataForTable: function(json) {    	
    	var records = [];
        for (var j in json) {
            records.push({recid: j, categoria: json[j].category, descricao: json[j].description , valor: json[j].value, data: json[j].date });
        }
       return records;
	},
	
	
    createTable: function(json) {
    	//get data to put in the table
    	
    	var data = MakeGraphic.getDataForTable(json);
    	console.log(data);
	    $(function () {    
	        $('#table').w2grid({ 
	            name: 'table', 
	            show: { 
	                toolbar: true,
	                footer: true,
	                toolbarAdd: true,
	                toolbarDelete: true,
	                toolbarSave: true,
	                toolbarEdit: true
	            },
	            searches: [                
	                { field: 'lname', caption: 'Last Name', type: 'text' },
	                { field: 'fname', caption: 'First Name', type: 'text' },
	                { field: 'email', caption: 'Email', type: 'text' },
	                { field: 'sdate', caption: 'Start Date', type: 'Date' }
	            ],
	            columns: [                
	                { field: 'recid', caption: 'ID', size: '10%', sortable: true, attr: 'align=center' },
	                { field: 'categoria', caption: 'categoria', size: '20%', sortable: true },
	                { field: 'descricao', caption: 'descricao', size: '30%', sortable: true },
	                { field: 'valor', caption: 'valor', size: '20%', sortable: true },
	                { field: 'data', caption: 'data', size: '20%', sortable: true },
	            ],
	            onAdd: function (event) {
	                w2alert('add');
	            },
	            onEdit: function (event) {
	                w2alert('edit');
	            },
	            onDelete: function (event) {
	                console.log('delete has default behaviour');
	            },
	            onSubmit: function (event) {
	                w2alert('save');
	            },
	            records: [   ]
	        });
	        w2ui['table'].add(MakeGraphic.getDataForTable(json));
	        
	    });
    }
};
