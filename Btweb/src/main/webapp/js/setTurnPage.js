/*
 * 设置翻页
 * isResetTurnPage是否需要清除翻页栏
 * 
 * */
function setTurnPage(isResetTurnPage,$pageUlId,pageCount,tranFunction)
{
	if(isResetTurnPage)
	{
		$pageUlId.empty();
		$pageUlId.append("<li><a href='javascript:carryTranFunction("+tranFunction+",1,false)'>&laquo;</a></li>");
		for(i=1;i<=pageCount;i++)
		{
			$pageUlId.append("<li><a href='javascript:carryTranFunction("+tranFunction+","+i+",false)'>"+i+"</a></li>");
		}
		$pageUlId.append("<li><a href='javascript:carryTranFunction("+tranFunction+","+pageCount+",false)'>&raquo;</a></li>");
	}
}

function carryTranFunction(carryFunction,num,isResetTurnPage)
{
	carryFunction(num,isResetTurnPage);
}