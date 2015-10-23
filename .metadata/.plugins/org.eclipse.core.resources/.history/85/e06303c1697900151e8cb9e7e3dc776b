var MakeGraphic = {

    getDataForGraphic: function(dateStart, dateEnd, category) {
        var ajax = ajaxInit();
        if (ajax) {
            var url = 'http://www.economy.zz.mu/ServletRelatory?dateStart='
                    + dateStart + '&dateEnd=' + dateEnd + '&category=' + category;
            ajax.open('GET', url, true);
            ajax.send();
        }
        ajax.onreadystatechange = function() {
            if (ajax.readyState == 4 && ajax.status == 200) {
                
                var options = MakeGraphic.getOptions();
                MakeGraphic.makeGraphic(ajax, options);
            }
        };
    },


    getOptions: function() {
        var options = {
                'width' : 450,
                'height' : 450,
                'title' : 'movimentacoes'
            };
        return options;
    },


    makeGraphic: function(ajax, options) {
        google.setOnLoadCallback(MakeGraphic.drawChart(ajax, options));
    },


    drawChart: function(ajax,options) {
        var jsonString = ajax.responseText;
        var json = JSON.parse(jsonString);

        var data = new google.visualization.DataTable();
        data.addColumn('string', 'categoria');
        data.addColumn('number', 'valor');
        for (var i = 0; i < json.length; i++) {
            data.addRow([ json[i].name, json[i].value ]);
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));

        google.visualization.events.addListener(chart, 'select', function(){
    		var selectedItem = chart.getSelection()[0];
    		var selected = data.getValue(selectedItem.row, 0);
    		
    		var dateS = document.getElementById('dateStart').value;
    		var dateE = document.getElementById('dateEnd').value;
    		
    		var ds = new Date(dateS);
    		var de = new Date(dateE);
    		var dateStart = ds.getTime();
    		var dateEnd = de.getTime();
    		MakeGraphic.getDataForDetailedGraphic(dateStart, dateEnd, selected);
        });       	
        chart.draw(data, options);
    },


    drawDetailedChart: function(ajax,options) {
        var jsonString = ajax.responseText;
        var json = JSON.parse(jsonString);

        var data = new google.visualization.DataTable();
        data.addColumn('string', 'date');
        data.addColumn('number', 'valor');
        for (var i = 0; i < json.length; i++) {
            data.addRow([ MakeGraphic.formatDate(json[i].date), json[i].value ]);
        };

        var chartDetailed = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chartDetailed.draw(data, options);
    },


    getDataForDetailedGraphic: function(dateS, dateE, subcategory) {

        var ajax = ajaxInit();
        if (ajax) {
            var url = 'http://www.economy.zz.mu/ServletDetailedGraphic?dateStart='
                    + dateS + '&dateEnd=' + dateE + '&subcategory=' + subcategory;
            ajax.open('GET', url, true);
            ajax.send();
        }
        ajax.onreadystatechange = function() {
            if (ajax.readyState == 4 && ajax.status == 200) {
                var options = MakeGraphic.getDetailedOptions(subcategory);
 
                MakeGraphic.drawDetailedChart(ajax,options);
            }
        };
    },

        
    getDetailedOptions: function(subcategory){
        var options = {
                'width' : 600,
                'height' : 500,
                'title' : subcategory
        };
        return options;
    },
    
     formatDate: function(input){
        var p = input.split(/\D/g);
        var result = [p[2],p[1],p[0]].join("/");   

        return result;
    }
};
