var MakeGraphic = {
		
		
		connection: FactoryConnection.getConnection(),
		
		getDataForGraphic: function(dateS, dateE, cat) {
		$.ajax({
			url: MakeGraphic.connection + '/ServletRelatory',
			
			data:{ dateStart: dateS , dateEnd: dateE, category: cat},
			
			success: function(jsonString){
				alert(jsonString);
				var json = JSON.parse(jsonString);
				alert(JSON.stringify(json));
//			    var jsonForGraphic = json[0];
//			    var jsonForTable = json[1];
				
			  
			    MakeGraphic.drawChart(json);
			}
			
		});
		
		
	},
	
	 getData: function(json){
        var list = [];
        for(var i =0;i<Object.keys(json).length; i++){
        	//alert(json[i].y);
            list.push(json[i].value);
           
        }
        alert('data : ' +list.toString());
       return list; 
       
    },
    
    
     getCategory: function(json){
        var list = [];
        for(var i =0;i<Object.keys(json).length; i++){
            list.push(json[i].name);
           //alert(json[i].name);
        }
        alert('categories: ' + list.toString());
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
                                alert('Category: ' + this.category + ', value: ' + this.y);
                                //MakeGraphic.getDataForDetailedGraphic(this.category);
                            }
                        }
                    }
                }
            },

            tooltip: {
                headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
            },

            series: [{
                name: "Brands",
                colorByPoint: true,
                data: MakeGraphic.getData(json)
            }],


            });
    },
    
    



    drawDetailedChart: function(ajax,options) {
        var jsonString = ajax.responseText;
        var json = JSON.parse(jsonString);
        
        //TODO get the data to put it in the chart (getCategory and getData
        
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
//            plotOptions: {
//                series: {
//                    cursor: 'pointer',
//                    point: {
//                        events: {
//                            click: function () {
//                                alert('Category: ' + this.category + ', value: ' + this.y);
//                                //MakeGraphic.getDataForDetailedGraphic(this.category);
//                            }
//                        }
//                    }
//                }
//            },

            tooltip: {
                headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
            },

            series: [{
                name: "Brands",
                colorByPoint: true,
                data: MakeGraphic.getData(json)
            }],


            });
    },


    getDataForDetailedGraphic: function(subcategory) {
    	//TODO get dateStart and dateEnd for this
        var ajax = ajaxInit();
        if (ajax) {
            var url = 'http://localhost:8080/Economy/ServletDetailedGraphic?dateStart='
                    + dateS + '&dateEnd=' + dateE + '&subcategory=' + subcategory;
            ajax.open('GET', url, true);
            ajax.send();
        }
        ajax.onreadystatechange = function() {
            if (ajax.readyState == 4 && ajax.status == 200) {
                MakeGraphic.drawDetailedChart(ajax);
            }
        };
    },

        
    
    
     formatDate: function(input){
        var p = input.split(/\D/g);
        var result = [p[2],p[1],p[0]].join("/");   

        return result;
    }
};
