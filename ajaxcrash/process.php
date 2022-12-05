<?php

echo 'processing';

//check for get variables and

if(isset($_GET['name'])){
    echo 'GET: your name is:'. $_GET['name'];
}