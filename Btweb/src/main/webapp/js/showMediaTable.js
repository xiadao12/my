/*展示视频列表*/
function showMediaTable($table,mediatData)
{
	for(var i=0;i<mediatData.length;i++)
	{
		if(i%5==0)
		{
			$table.append("<tr></tr>");
		}
		$table.children("tr").last().append(
				"<td style='border:1px solid rgb(241, 242, 243);font-size:13px;'>"
					+"<a href='"+getPath()+"/jsp/mediaDetail.jsp?mediaId="+mediatData[i].id+"' data-toggle='tooltip' title='"+mediatData[i].name+"'>"
						+"<div style='width:177px;height:250px;'>"
						/*+"<div style='width:9.218%;height:23.148%;border:10px solid black;'>"*/
						/*+"<div style='width:60%;height:60%;border:10px solid black;'>"*/
							+"<img style='width:100%;height:100%;' src='"+mediatData[i].coverUrl+"'></img>"
							/*+"<img width='92.18%' height='213.48%' src='"+mediatData[i].coverUrl+"'></img>"*/
						+"</div>"
						+"<div style='width:177px;white-space:nowrap;text-overflow:ellipsis;word-break:break-all;overflow:hidden;'>"
							+"<label>"+mediatData[i].name+"</label>"
						+"</div>"
					+"</a>"
					+"<div style='width:177px;white-space:nowrap;text-overflow:ellipsis;word-break:break-all;overflow:hidden;'>"
						+"<label>"+mediatData[i].releaseYear+"</label>"
						+"<label>"+mediatData[i].area+"</label>"
						+"<label>"+mediatData[i].styles+"</label>"
					+"</div>"
			+	"</td>");
	}
}