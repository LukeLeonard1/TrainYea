<?php
    session_start();
    
    // connect to the database
    $db = mysqli_connect('localhost', 'root', '', 'TrainYea');
    
    if (!$db)
    {
        die("Connection failed: " . mysqli_connect_error());
    }
    
?>
