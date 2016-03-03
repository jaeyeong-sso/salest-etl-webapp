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
    
    <!-- Bootstrap Core CSS -->
    <link href="/static/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/static/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/static/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/static/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
	<!-- DataTables Responsive CSS -->
    <link href="/static/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">

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
                    <h1 class="page-header">Batch Job Execution Status</h1>
                </div>
            </div>
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Batch Job Execution Status
                        </div>
                        <!-- .panel-heading -->
                        <div class="panel-body">
                            <div class="panel-group" id="accordion">
                            
                            	<c:if test="${not empty batchJobs}">
									<c:forEach var="batchJob" items="${batchJobs}">
						
	                                <div class="panel panel-default">
	                                    <div class="panel-heading">
	                                        <h4 class="panel-title">
	                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
	                                            	<span><i class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;${batchJob.batchJobInstance.getJob_name()}</span>
													<span class="pull-right text-muted small"><em>End Time : ${batchJob.getEnd_time().toString()}</em></span>
													<span class="pull-right text-muted small"><em>&nbsp;|&nbsp;</em></span>
													<span class="pull-right text-muted small"><em>Start Time : ${batchJob.getStart_time().toString()}</em></span>
													<span class="pull-right text-muted small"><em>&nbsp;|&nbsp;</em></span>
													<span class="pull-right text-muted small"><em><b>${batchJob.getStatus()}</b></em></span>	
	                                            </a>
	                                        </h4>
	                                    </div>
	                                    <div id="collapseOne" class="panel-collapse collapse in">
	                                        <div class="panel-body">
	                                        	<div class="list-group">
													<c:if test="${not empty batchJob.batchStepExecutions}">
													<c:forEach var="stepJob" items="${batchJob.getBatchStepExecutions()}">
								
														<a href="javascript:;" onclick="alert('ok');" class="list-group-item">
																
																<i class="glyphicon glyphicon-ok"></i>&nbsp;&nbsp;${stepJob.getStep_name()}

																<span class="pull-right text-muted small"><em>End Time : ${stepJob.getEnd_time().toString()}</em></span>
																<span class="pull-right text-muted small"><em>&nbsp;|&nbsp;</em></span>
																<span class="pull-right text-muted small"><em>Start Time : ${stepJob.getStart_time().toString()}</em></span>
																<span class="pull-right text-muted small"><em>&nbsp;|&nbsp;</em></span>
																<span class="pull-right text-muted small"><em><b>${stepJob.getStatus()}</b></em></span>		
														</a>
													</c:forEach>
													</c:if>
												</div>
	                                        </div>
	                                    </div>
	                                </div>
                                
									</c:forEach>
								</c:if>
                                
                            </div>
                        </div>
                        <!-- .panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
			<!-- /.row -->		
			<div class="row">
				<div class="panel panel-default">
 					<div class="panel-heading">Daily Aggregation Record Summary</div>
						<!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Record Date</th>
                                            <th>Number of Transactions</th>
                                            <th>Total Sales Amount</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Earliest Record</td>
                                            <td>${earliestDateItem.getDate().toString()}</td>
                                            <td>${earliestDateItem.getNumOfOrder()}</td>
                                            <td>${earliestDateItem.getTotalAmount()}</td>
                                        </tr>
                                        <tr>
                                            <td>Latest Record</td>
                                            <td>${latestDateItem.getDate().toString()}</td>
                                            <td>${latestDateItem.getNumOfOrder()}</td>
                                            <td>${latestDateItem.getTotalAmount()}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
            </div>
            <!-- /.row -->
            
        </div>

        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    
    <!-- jQuery -->
    <script src="/static/bower_components/jquery/dist/jquery.min.js"></script>
    
    <!-- Bootstrap Core JavaScript -->
    <script src="/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/static/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/static/dist/js/sb-admin-2.js"></script>
    
    <!-- DataTables JavaScript -->
    <script src="/static/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="/static/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    <script src="/static/bower_components/datatables-responsive/js/dataTables.responsive.js"></script>
	
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
                responsive: true
        });
    });
    </script>
    
</body>

</html>
