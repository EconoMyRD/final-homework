var logoffSystem =
{
	
	init: function()
    {
		logoffSystem.setClick();
	},
	
	setClick: function() 
    {
		var a = document.getElementById('exit');
		a.addEventListener('click', function(){
			logoffSystem.exitSession();		
		});		
	},
    
    exitSession: function()
    {
       var ajax = ajaxInit(),
			url = FactoryConnection.getConnection() + '/logoff';
		ajax.open('GET', url, true);
		ajax.send();
		ajax.onreadystatechange = function() 
        {
			if(ajax.readyState==4 && ajax.status==200)
            {
				window.location.href= FactoryConnection.getConnection() + '/index.html';		
			}
		}; 
    }
};


logoffSystem.init();