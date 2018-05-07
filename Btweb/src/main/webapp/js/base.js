//获取项目名称路径  /Btweb
function getPath(){
	var pathName = document.location.pathname;
 	var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
 }

function judgeIsNull(obj){
	if(obj == null || obj == "")
	{
		return true;
	}
	return false;
}