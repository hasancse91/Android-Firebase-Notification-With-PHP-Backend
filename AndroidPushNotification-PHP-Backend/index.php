<?php session_start(); ?>
<html>
    <head>
        <title>Firebase Push Notification System on Android</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="//www.gstatic.com/mobilesdk/160503_mobilesdk/logo/favicon.ico">
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
 
        <style type="text/css">
            body{
            }
            div.container{
                width: 1000px;
                margin: 0 auto;
                position: relative;
            }
            legend{
                font-size: 30px;
                color: #555;
            }
            .btn_send{
                background: #00bcd4;
            }
            label{
                margin:10px 0px !important;
            }
            textarea{
                resize: none !important;
            }
            .fl_window{
                width: 400px;
                position: absolute;
                right: 0;
                top:100px;
            }
            pre, code {
                padding:10px 0px;
                box-sizing:border-box;
                -moz-box-sizing:border-box;
                webkit-box-sizing:border-box;
                display:block; 
                white-space: pre-wrap;  
                white-space: -moz-pre-wrap; 
                white-space: -pre-wrap; 
                white-space: -o-pre-wrap; 
                word-wrap: break-word; 
                width:100%; overflow-x:auto;
            }
 
        </style>
    </head>
    <body>
        
        <?php

                if($_POST['user_id']=="hellohasan.com" && $_POST['password']=="123"){
                    $_SESSION['status'] = "logged_in";
                    echo "<script>window.location='dashboard.php'</script>";
                }


        ?>


        <div class="container">
            <div class="fl_window">
                <div><img src="https://1.bp.blogspot.com/-YIfQT6q8ZM4/Vzyq5z1B8HI/AAAAAAAAAAc/UmWSSMLKtKgtH7CACElUp12zXkrPK5UoACLcB/s1600/image00.png" width="200" alt="Firebase"/></div>
                <br/>
                <?php if ($json != '') { ?>
                    <label><b>Request:</b></label>
                    <div class="json_preview">
                        <pre><?php echo json_encode($json) ?></pre>
                    </div>
                <?php } ?>
                <br/>
                <?php if ($response != '') { ?>
                    <label><b>Response:</b></label>
                    <div class="json_preview">
                        <pre><?php echo json_encode($response) ?></pre>
                    </div>
                <?php } ?>
 
            </div>

 
            <form class="pure-form pure-form-stacked" method="post">
                <fieldset>
                    <legend>Firebase Push Notification System on Android</legend>
 
                    <label for="title1">User ID</label>
                    <input type="text" id="user_id" name="user_id" class="pure-input-1-2" value="hellohasan.com">
 
                    <label for="message1">Password</label>
                    <input type="Password" id="Password" name="password" class="pure-input-1-2" value="123">

                    <br><br>
                    
                    <input type="hidden" name="push_type" value="topic"/>
                    <button type="submit" class="pure-button pure-button-primary btn_send">Send Notification</button>
                </fieldset>
            </form>
        </div>
    </body>
</html>