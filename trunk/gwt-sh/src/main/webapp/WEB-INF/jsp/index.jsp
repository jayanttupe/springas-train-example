<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>COMAC Spare Part Management System Enterprise Edition </title>
     <!--CSS for loading message at application Startup-->
    <style type="text/css">
        body { overflow:hidden }
        #loading {
            border: 1px solid #ccc;
            position: absolute;
            left: 25%;
            top: 40%;
            padding: 2px;
            z-index: 20001;
            height: auto;
        }
        #loading a {
            color: #225588;
        }
        #loading .loadingIndicator {
            background: white;
            font: bold 13px tahoma, arial, helvetica;
            padding: 10px;
            margin: 0;
            height: auto;
            color: #444;
        }

        #loadingMsg {
            font: normal 10px arial, tahoma, sans-serif;
        }

        .blackText,
        .blackOnWhite {
            color: black;
            font-family: Verdana, Bitstream Vera Sans, sans-serif;
            font-size: 11px;
        }

        .blackOnWhite {
            background-color: white;
        }

        .sampleName {
            color:#8FB34F;
            font-weight:bold;
            text-decoration:none;
        }

        .exampleSeperator {
            border-bottom: 2px solid gray;
            background-color:white; color:black;
            font-weight: bold;
            text-align: left;
        }

        .explorerCheckErrorMessage {
            color: red;
            font-family:Georgia,serif;
            font-size:13px;
        }
    </style>
</head>
<!--Host HTML file-->
<body>

<!--add loading indicator while the app is being loaded-->
<div id="loadingWrapper">
<div id="loading">
    <div class="loadingIndicator">
        <!--<img src="images/pieces/48/cube_green.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>COMAC SPMS Enterprise Edition<br/>-->
        <img src="images/logo.png" width="240" height="25" style="margin-right:8px;float:left;vertical-align:top;"/>COMAC Spares Part Management System Enterprise Edition<br/>
        <span id="loadingMsg">Loading styles and images ...</span></div>
</div>
</div>
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading Fusion Charts API...';</script>
<SCRIPT language="Javascript" src="FusionCharts/JSClass/FusionCharts.js"></SCRIPT>
<!-- IMPORTANT : You must set the variable isomorphicDir to [MODULE_NAME]/sc/ so that the SmartGWT resource are 
  correctly resolved -->	
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading Core API ....';</script>
<script> var isomorphicDir = "spms/sc/"; </script>
<script language="Javascript" src=spms/sc/modules/ISC_Core.js?isc_version=8.0.js></script>
<script language="Javascript" src=spms/sc/modules/ISC_Foundation.js?isc_version=8.0.js></script>
<script language="Javascript" src=spms/sc/modules/ISC_Containers.js?isc_version=8.0.js></script>
<script language="Javascript" src=spms/sc/modules/ISC_Grids.js?isc_version=8.0.js></script>
<script language="Javascript" src=spms/sc/modules/ISC_Forms.js?isc_version=8.0.js></script>
<script language="Javascript" src=spms/sc/modules/ISC_DataBinding.js?isc_version=8.0.js></script>
<script language="Javascript" src="spms/sc/modules/ISC_Calendar.js?isc_version=8.0.js" ></script>
<script language="Javascript" src="spms/sc/modules/ISC_RichTextEditor.js?isc_version=8.0.js" ></script>
<script language="Javascript" src="spms/sc/modules/ISC_Scheduler.js?isc_version=8.0.js" ></script>
<script language="Javascript" src="spms/sc/modules/ISC_PluginBridges.js?isc_version=8.0.js" ></script>

<script>
function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

// Determine what skin file to load
var currentSkin = readCookie('skin');
if (currentSkin == null){
	currentSkin = "EnterpriseBlue";
}
</script>
 <!-- 
<script src="dsLoader.mockTestPage.form"></script>

-->

<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading DataSource ...';</script>
<div id="loadDsMock" ></div>
<script type="text/javascript"  src="shared/ds/loadJs.js" >
</script>

<!--load skin-->
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading Skin ...';</script>
<script type="text/javascript">
document.write("<"+"script src=spms/sc/skins/" + currentSkin + "/load_skin.js?isc_version=8.0.js><"+"/script>");
</script>

<!--include the application JS-->
<script type="text/javascript" language="javascript" src="spms/spms.nocache.js"></script>
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Login System, Please Wait ...';</script>
<!-- history support -->
<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
        style="position:absolute;width:0;height:0;border:0"></iframe>

</body>
</html>
