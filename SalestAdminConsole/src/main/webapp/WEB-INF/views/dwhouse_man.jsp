<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sales Temperature - Do you business with your insight!</title>
	
	<!-- JQuery & js-cookie -->	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/js-cookie/2.1.0/js.cookie.min.js"></script>
    
    <!-- Bootstrap Core CSS -->
    <link href="/static/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/static/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="/static/dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/static/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/static/bower_components/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/static/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	

	<style type="text/css">
		.btn-file {
		  position: relative;
		  overflow: hidden;
		}
		.btn-file input[type=file] {
		  position: absolute;
		  top: 0;
		  right: 0;
		  min-width: 100%;
		  min-height: 100%;
		  font-size: 100px;
		  text-align: right;
		  filter: alpha(opacity=0);
		  opacity: 0;
		  background: red;
		  cursor: inherit;
		  display: block;
		}
		input[readonly] {
		  background-color: white !important;
		  cursor: text !important;
		}
	</style>
	
		<script>
	    $(document).ready(function() {
	        $('#uploadBtn').click(function() {

	            var formData = new FormData();
	            $.each($('#attachFile')[0].files, function(i, file) {          
	            	formData.append('file', file);
	            });
	             
	            $.ajax({
	                url: "/SalestAdminConsole/api/upload/tr_csv",
	                type: 'POST',
	                contentType: false,
	                data: formData,
	                processData: false,
	                success: function(data, textStatus, jqXHR) {
	                	//alert('Success');
	                }, error: function(jqXHR, textStatus, errorThrown) {
	                	alert('Failed');
	                }
	            });
	        });
	        
	        $('#runTestBatchBtn').click(function() {
	            $.ajax({
	                url: "/SalestAdminConsole/api/rawdata/agg_tr_daily",
	                type: 'POST',
	                contentType: false,
	                processData: false,
	                success: function(data, textStatus, jqXHR) {
	                		//alert('ajax ok');
		                }, error: function(jqXHR, textStatus, errorThrown) {
		                	alert('ajax failed');
		                }
	            });
	        });
	        
	        $('#printResultBtn').click(function() {
	            $.ajax({
	                url: "/SalestAdminConsole/api/rawdata/print_result",
	                type: 'POST',
	                contentType: false,
	                processData: false,
	                success: function(data, textStatus, jqXHR) {
	                		//alert('ajax ok');
		                }, error: function(jqXHR, textStatus, errorThrown) {
		                	alert('ajax failed');
		                }
	            });
	        });
	        
	    });
	   	
	    <!--
	    $(document).on('change', '.btn-file :file', function() {
	    	  var input = $(this),
	    	      numFiles = input.get(0).files ? input.get(0).files.length : 1,
	    	      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	    	  input.trigger('fileselect', [numFiles, label]);
	    });
	    -->

	    $(document).ready( function() {
	    	    $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
	    	        
	    	        var input = $(this).parents('.input-group').find(':text'),
	    	            log = numFiles > 1 ? numFiles + ' files selected' : label;
	    	        
	    	        if( input.length ) {
	    	            input.val(log);
	    	        } else {
	    	            if( log ) alert(log);
	    	        }
	    	        
	    	    });
	    });


    	function showProcessMsg(bShow, message){
	    	if(bShow == true){
	    		$('div#processMsg').show();
	    	} else {
	    		$('div#processMsg').hide();
	    	}
    	}
    	
	    $(document).ready(
	    	showProcessMsg(false, "message")
	    );
	    
	</script>
	
	
	<script type="text/javascript">
	
		//var PresentCapacity_= <c:out value="${PresentCapacity}"/> 
		//var ConfiguredCapacity_ = <c:out value="${ConfiguredCapacity}"/> 
		
		$(function() {
			
			$.ajax({
				type: "GET",
				dataType: "json",
				contentType: "application/json",
				url: "/SalestAdminConsole/api/viewdata/cluster_info",
				success: function (response) {
					alert(response.configured_capacity);
					/*
			                Morris.Donut({
			                    element: 'donut-example',
			                    data: response
			                });
					*/
			            },
            	error: function () {
					alert("Error loading data! Please try again.");
				}
			});
			
			/*
			var PresentCapacity= 60;
			var ConfiguredCapacity = 40;
		
			var dataArr = [{label: PresentCapacity_, value: PresentCapacity}, 
			               {label: "Used",value: ConfiguredCapacity}];
	    	
		    Morris.Donut({
		        element: 'hdfs-usage-donut-chart',
		        data: dataArr,
		        resize: true
		    });
		    */
		})
		
	</script>
	
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/SalestAdminConsole/web/dwhouse_man">Sales Temperature - Admin Console</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
						<li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a></li>
						<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
						<li class="divider"></li>
						<li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
					</ul>
				</li>
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="/SalestAdminConsole/web/dwhouse_man.jsp"><i class="fa fa-database fa-fw"></i> DW Management</a>
                        </li>
                        <li>
                            <a href="/SalestAdminConsole/web/batchjob_status.jsp"><i class="glyphicon glyphicon-align-left"></i> Batch Job Status</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Data Warehouse Management</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
            	<div class="col-lg-12">
            		<div class="col-lg-6">
						<div class="panel panel-default" >
							<div class="panel-heading"><i class="fa fa-database fa-fw"></i> HDFS Usage Status</div>
							<div class="panel-body">
								<div id="hdfs-usage-donut-chart"></div>
							</div>
						</div>
					</div>
			        <div class="col-lg-6">
			        
						<div class="panel panel-default">
	                        <div class="panel-heading"><i class="glyphicon glyphicon-align-left"></i> Node Information</div>
	                        <div class="panel-body">
	                        
	                        	<c:if test="${not empty hdfsNodesInfo}">

									<c:forEach var="NodeInfo" items="${hdfsNodesInfo}">

										<div class="panel panel-green">
											<div class="panel-heading">${NodeInfo.hostname}</div>
											<div class="panel-body">
											<div class="list-group">
												<a href="#" class="list-group-item">
													<i class="glyphicon glyphicon-info-sign"></i>&nbsp;&nbsp;&nbsp;Address
													<span class="pull-right text-muted small"><em>${NodeInfo.name}</em></span>
												</a>
												<a href="#" class="list-group-item">
													<i class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;&nbsp;Used
													<span class="pull-right text-muted small"><em>${NodeInfo.dfs_used}</em></span>
												</a>
												<a href="#" class="list-group-item">
													<i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;&nbsp;Remaining
													<span class="pull-right text-muted small"><em>${NodeInfo.dfs_remaining}</em></span>
												</a>
											</div>
											</div>
										</div>
				
									</c:forEach>

								</c:if>				
						</div>
	
					</div>
                </div>
			</div>
			
            <!--
            <div class="row">
            	<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-info-sign fa-fw"></i> Notification
						</div>
						<div class="panel-body">
							<div id="processMsg" class="alert alert-warning alert-dismissable">
								<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
								successfully uploaded ! <a href="#" class="alert-link">Alert Link</a>.
							</div>
						</div>
					</div>
				</div>
            </div>
            --> 
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                
                    <div class="panel panel-default">
                    
                        <div class="panel-heading">
                            <i class="fa fa-upload fa-fw"></i> Upload CSV data file

                            <div class="pull-right">
								<div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                        Actions<span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
	                                        <li><a href="javascript:ajaxCSRFPost()">Action</a></li>
	                                        <li><a href="#">Another action</a></li>
	                                        <li><a href="#">Something else here</a></li>
	                                        <li class="divider"></li>
	                                        <li><a href="#">Separated link</a></li>
	                                </ul>
								</div>
                            </div>
                        </div>
                        
						<div class="panel-body">
						
							<form id="submitForm" enctype="multipart/form-data">
								<div class="input-group">
									<span class="input-group-btn">
										<span class="btn btn-primary btn-file">
											Browse&hellip; <input type="file" id="attachFile">
										</span>
									</span>
									<input type="text" class="form-control" readonly>
									<span class="input-group-btn">
										<span id="uploadBtn" class="btn btn-primary btn-file">upload</span>
									</span>
								</div>
							</form>
							
							</p>
							
							<div class="panel panel-default">
                        		<div class="panel-heading"> Files info. </div>
								<div class="panel-body">
 									<div class="table-responsive">
										<table class="table table-striped table-bordered table-hover">
		                                    <thead>
		                                        <tr>
		                                            <th>File name</th>
		                                            <th>File path</th>
		                                            <th>File size</th>
		                                            <th>Update Time</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                        <tr>
		                                            <td>transaction_receipt.data</td>
		                                            <td>/salest/raw_data/</td>
		                                            <td>128KB</td>
		                                            <td>2016/02/04 14:00:20</td>
		                                        </tr>
		                                    </tbody>
										</table>
									</div>
								</div>
							</div>
	
						</div>

                    </div>
                </div>

            </div>
            <!-- /.row -->
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-upload fa-fw"></i> Test Run Batch Job(Aggregate Daily Receipt Transaction)
                        </div>
                        <!-- /.panel-heading -->
						<div class="panel-body">
							<button id="runTestBatchBtn" type="button" class="btn btn-primary">Run it!</button>
						</div>
                        <!-- /.panel-body -->
                    </div>
                </div>
            </div>
            <!-- /.row -->
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-upload fa-fw"></i> Batch Job Execution Test
                        </div>
                        <!-- /.panel-heading -->
						<div class="panel-body">
							<button id="printResultBtn" type="button" class="btn btn-primary">Do it!</button>
						</div>
                        <!-- /.panel-body -->
                    </div>
                </div>
            </div>
            <!-- /.row -->
            
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    
    
    <!-- jQuery -->
    <!--
    <script src="/static/bower_components/jquery/dist/jquery.min.js"></script>
	-->
    <!-- Bootstrap Core JavaScript -->
    <script src="/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/static/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="/static/bower_components/raphael/raphael-min.js"></script>
    <script src="/static/bower_components/morrisjs/morris.min.js"></script>
    <!-- 
    <script src="/static/js/morris-data.js"></script>
	-->
	
    <!-- Custom Theme JavaScript -->
    <script src="/static/dist/js/sb-admin-2.js"></script>
    
</body>

</html>
