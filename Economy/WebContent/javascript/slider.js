var slider = {   

        slide1: function(){
            document.getElementById('id').src="imagens/slide/1.jpg";
            setTimeout("slider.slide2()", 6000);
        },

        
        slide2: function(){
            document.getElementById('id').src="imagens/slide/3.jpeg";
            setTimeout("slider.slide1()", 6000);
        }

};