var logoffSystem =
{
	connection: FactoryConnection.getConnection(),
	
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
//    	$.ajax({
//    		url : logoffSystem.connection  + '/resourceslogout',
//    		method: 'POST',
//    		
//    		success: function() {
//    			window.location.href= logoffSystem.connection  + '/index.html';		
//				
//			}
//    	});
    	sessionStorage.removeItem('userId');
    	window.location.href= logoffSystem.connection  + '/index.html';
    }
};


logoffSystem.init();