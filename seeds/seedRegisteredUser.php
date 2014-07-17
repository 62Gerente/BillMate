<?php
/*
 * BillMate Users Seed
 */

include('includes/_env.php');
include('includes/_utilities.php');
include('includes/_database.php');

$usersCount = $argv[1] ? $argv[1] : 10;

function fetchUsers($usersCount){
    $randomUserURL = 'http://api.randomuser.me/?results=' . $usersCount;

    $randomUser = curl_init($randomUserURL);
    curl_setopt($randomUser, CURLOPT_RETURNTRANSFER, true);
    $response = curl_exec($randomUser);
    curl_close($randomUser);

    return $response;
}

function sendUserData($data, $returnResponse = false){
    return sendData($data, $returnResponse, 'http://' . HOST . '/BillMate/register/save');
}

$maxUsers = 20;
$usersCount = $argv[1] ? $argv[1] : 10;
$rounds = ceil($usersCount / $maxUsers);

if(file_exists(OUTPUT_DIR . '/users.json.bak') === false){
    $allUsers = json_decode(file_get_contents('database/users.json'), true);
    $countAllUsers = count($allUsers['results']);
    $output = false;
    echo "Fetching users from cached file..." . PHP_EOL;
}

// Add Users
$added = 0;
$registeredIDs = array();
$registeredUsers = array('results' => array());
$users = &$allUsers['results'];
for($i = 0; $i < $usersCount; $i++){
    $resultStatus = sendUserData($users[$i]);
}

$databaseUsers = getAllFromTable(dbConnect());
file_put_contents(OUTPUT_DIR . '/users.json', json_encode($databaseUsers));

echo 'Finished. There are ' . count($databaseUsers) . ' on database' . PHP_EOL;
