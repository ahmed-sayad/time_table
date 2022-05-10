<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['newtask']) && isset($_POST['time'])) {
    if ($db->dbConnect()) {
        if ($db->addNewTask("tasks", $_POST['newtask'], $_POST['time'])) {
            echo "Task has been Added Successfully";
        } else echo "Task Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required to add new task";
?>