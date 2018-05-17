/*展示视频列表*/
function showMediaDiv($div,mediatData)
{
	for(var i=0;i<mediatData.length;i++)
	{
		$div.append(
				"<div style='border:1px solid rgb(241, 242, 243);font-size:13px;margin-right:0.9%;margin-top:0.6%;'>"
					+"<a href='"+getPath()+"/jsp/mediaDetail.jsp?mediaId="+mediatData[i].id+"' data-toggle='tooltip' title='"+mediatData[i].name+"'>"
						+"<div style='width:177px;height:250px;'>"
							+"<img style='width:100%;height:100%;' src='"+mediatData[i].coverUrl+"'></img>"
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
				+"</div>"
		);
	}
}