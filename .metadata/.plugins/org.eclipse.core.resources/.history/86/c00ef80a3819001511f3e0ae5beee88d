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
			url = 'http://localhost:8080/Economy/logoff';
		ajax.open('GET', url, true);
		ajax.send();
		ajax.onreadystatechange = function() 
        {
			if(ajax.readyState==4 && ajax.status==200)
            {
				window.location.href= 'http://localhost:8080/Economy/html/index.html';		
			}
		}; 
    }
};


logoffSystem.init();