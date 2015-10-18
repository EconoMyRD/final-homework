var xmlhttp;  

 function ajaxInit(){
    try {
        xmlhttp = new XMLHttpRequest();
    } catch (ee) {
        try {
           AjaxInit.xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (E) {
                   xmlhttp = false;
                }
            }
        }

        return xmlhttp;
 }

