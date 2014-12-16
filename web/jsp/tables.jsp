<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>按表格查看动作数据</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <%@include file="header.html"%>
	</head>

	<body>
        <jsp:include page="navbar.jsp"/>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<jsp:include page="sidebar.jsp" />

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="#">Tables</a>
							</li>
							<li class="active">Simple &amp; Dynamic</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
							    表格
								<small>
									<i class="icon-double-angle-right"></i>
									传感器数据
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                <h3 class="header smaller lighter blue">数据采集汇总表</h3>
                                <div class="table-header">
                                    全部的采集数据
                                </div>
								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table id="sample-table-1" class="table table-striped table-bordered table-hover table-condensed">
												<thead>
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th>type</th>
														<th>position</th>
														<th>
                                                            <i class="icon-time"></i>
                                                            timestamp
                                                        </th>
                                                        <th>imei</th>
														<th>number</th>
														<th>uuid</th>
														<th></th>
													</tr>
												</thead>
												<tbody>
                                                    <c:forEach items="${lists}" var="sensordata">
                                                        <tr>
                                                            <td class="center">
                                                                <label>
                                                                    <input type="checkbox" class="ace" />
                                                                    <span class="lbl"></span>
                                                                </label>
                                                            </td>
                                                            <td>${sensordata.type}</td>
                                                            <td>${sensordata.position}</td>
                                                            <td>${sensordata.timestamp}</td>
                                                            <td>${sensordata.imei}</td>
                                                            <td>${sensordata.number}</td>
                                                            <td>${sensordata.uuid}</td>
                                                            <td>
                                                                <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                                                                    <a class="blue" href="#">
                                                                        <i class="icon-zoom-in bigger-130"></i>
                                                                    </a>

                                                                    <a class="green" href="#">
                                                                        <i class="icon-pencil bigger-130"></i>
                                                                    </a>

                                                                    <a class="red" href="#">
                                                                        <i class="icon-trash bigger-130"></i>
                                                                    </a>
                                                                </div>

                                                                <div class="visible-xs visible-sm hidden-md hidden-lg">
                                                                    <div class="inline position-relative">
                                                                        <button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
                                                                            <i class="icon-caret-down icon-only bigger-120"></i>
                                                                        </button>

                                                                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
                                                                            <li>
                                                                                <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
                                                                                    <span class="blue">
                                                                                        <i class="icon-zoom-in bigger-120"></i>
                                                                                    </span>
                                                                                </a>
                                                                            </li>

                                                                            <li>
                                                                                <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                                                                    <span class="green">
                                                                                        <i class="icon-edit bigger-120"></i>
                                                                                    </span>
                                                                                </a>
                                                                            </li>

                                                                            <li>
                                                                                <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                                                                    <span class="red">
                                                                                        <i class="icon-trash bigger-120"></i>
                                                                                    </span>
                                                                                </a>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
												</tbody>
											</table>
										</div><!-- /.table-responsive -->
									</div><!-- /span -->
								</div><!-- /row -->

								<div class="hr hr-18 dotted hr-double"></div>


                                <table data-toggle="table"
                                        data-url="default"
                                        data-search="true"
                                        data-show-refresh="true"
                                        data-show-toggle="true"
                                        data-show-columns="true">
                                    <thead>
                                        <th data-field="type">type</th>
                                        <th data-field="position">position</th>
                                        <th data-field="timestamp">
                                            <i class="icon-time"></i>
                                            timestamp
                                        </th>
                                        <th data-field="imei">imei</th>
                                        <th data-field="number">number</th>
                                        <th data-field="uuid">uuid</th>
                                    </thead>
                                </table>

							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				<%@include file="ace_settings.html"%>
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

        <%@include file="footer.html"%>
	    <!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($){

                var oTable1 = $('#sample-table-1').dataTable( {
                    "aoColumns": [
                        { "bSortable": false },
                        null,null,null,null,null,null,
                        { "bSortable": false }
                    ] } );

				var oTable2 = $('#sample-table-2').dataTable( {
				"aoColumns": [
			      { "bSortable": false },
			      null, null,null, null, null,
				  { "bSortable": false }
				] } );

                $('table tr td a.blue').on('click',function(){
                    console.log("on click");
                    detailTable();
                });

                function detailTable(){
                    var trobj=$(this).parent().parent().parent();
                    var td=$(trobj).children('td');
                    var uuidobj=$(td).eq(6);
                    var uuid=$(uuidobj).text();
                    var url='/detailTable?uuid='+uuid;
                    $.get(url,function(data){
                        alert("get data");
                        drawTable(data);
                    });
                }

                function drawTable(data){
                    alert("draw table");
                }

                $('table tr td a.green').on('click',function(){
                    alert("on click");
                });

                $('table tr td a.red').on('click',function(){
                    alert("on click");
                });

				$('table th input:checkbox').on('click' , function(){
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
						
				});
			
			
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			})
		</script>
</body>
</html>
