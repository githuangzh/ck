$(function(){
	$('#navigations').scojs_tab(
		{
			content:"#nav-pane",
			active:0
		}
	);
	
	function tabClose(obj){
		$(obj).dblclick(function () {
			var pane_id = $(this).attr("data-target");
			$(pane_id).remove();
			$(this).parent().prev().find('> a').trigger('click');
			$(this).parent().remove();
        });
	}
	
	function addTab(title,url,id){
		var tab = "<li class='tab' id='tab_"+id+"'><a data-trigger='ajax' href='"+url+"' data-target='#pane_"+id+"'>"+title+"</a></li>";
		var pane = "<div class='tab-content' id='pane_"+id+"' title='"+title+"'></div>";
		$tabs = $("#navigations");
		$pane = $("#nav-pane");
		if($("#tab_"+id).length > 0){
			selectTab(id);
		}else{
			$pane.append(pane);
			$tabs.append(tab);
			selectTab(id);
			tabClose($("#tab_"+id+" a"));
		}
		
		
	}
	
	function selectTab(id){
		$("#tab_"+id+" a").trigger('click');
		$("#nav-pane div").removeClass("active");
		$("#pane_"+id).addClass("active");
		$("#tab_"+id+" a").click(function(){
			$("#nav-pane div").removeClass("active");
			$("#pane_"+id).addClass("active");
		});
	}
	
	
	$(".nav-list li a").click(function(){
		var id = $(this).attr("id");
		id = id.substring("res_list".length);
		var url = $(this).attr("url");
		var title = $(this).attr("content");
		url = url == 'javascript:void(0)' ? '' : url;
		if(url==''){
 		   return false;
	    }
		addTab(title,url,id);
	});
	
});







