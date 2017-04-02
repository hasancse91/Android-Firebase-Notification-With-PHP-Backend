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

        if($_SESSION['status']=="logged_in"){


            // Enabling error reporting
            error_reporting(-1);
            ini_set('display_errors', 'On');
     
            require_once __DIR__ . '/firebase.php';
            require_once __DIR__ . '/push.php';
     
            $firebase = new Firebase();
            $push = new Push();
     
            
            
            // notification title
            $title = isset($_GET['title']) ? $_GET['title'] : '';
             
            // notification message
            $message = isset($_GET['message']) ? $_GET['message'] : '';
             
            // push type - single user / topic
            $push_type = isset($_GET['push_type']) ? $_GET['push_type'] : '';
             
            // whether to include to image or not
            $image_url = isset($_GET['image_url']) ? $_GET['image_url'] : '';
            

            // optional payload
            // set article data. you can add more data as "key-value pair" in payload array
            $payload = array();
            $payload['article_data'] = isset($_GET['article_data']) ? $_GET['article_data'] : '';

     
            $push->setTitle($title);
            $push->setMessage($message);
            $push->setImage($image_url);
            $push->setIsBackground(FALSE);
            $push->setPayload($payload);
     
     
            $json = '';
            $response = '';
     
            if ($push_type == 'topic') {
                $json = $push->getPush();
                $response = $firebase->sendToTopic('global', $json);
            } else if ($push_type == 'individual') {
                $json = $push->getPush();
                //$regId = isset($_GET['regId']) ? $_GET['regId'] : '';
                $regId = "PUT YOUR DEVICE ID HERE";
                $response = $firebase->send($regId, $json);
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

     
                <form class="pure-form pure-form-stacked" method="get">
                    <fieldset>
                        <legend>Firebase Push Notification System on Android</legend>
     
                        <label for="title1">Title</label>
                        <input type="text" id="title1" name="title" class="pure-input-1-2" placeholder="Enter title">
     
                        <label for="message1">Message</label>
                        <input class="pure-input-1-2" name="message" id="message1" placeholder="Short message"></input>

                        <label for="message1">Article</label>
                        <textarea class="pure-input-1-2" name="article_data" id="article_id" rows="3" placeholder="Enter Full Article"></textarea>

                        <label for="message1">Image URL</label>
                        <input type="text" id="image_url_id" name="image_url" class="pure-input-1-2" placeholder="Enter Image URL">
     
                        <input type="hidden" name="push_type" value="topic"/>
                        <button type="submit" class="pure-button pure-button-primary btn_send">Send Notification</button>
                    </fieldset>
                </form>

                <a href="dashboard.php">Reset</a> - <a href="logout.php">Logout</a>
                <br><br>
            </div>

            

        <?php } 
        else{
            echo "You are not an authorized user!";
            echo "<br><a href='index.php'>Login Page</a>";
        }
        ?>
    </body>
</html>