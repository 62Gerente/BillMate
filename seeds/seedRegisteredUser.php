<?php
/*
 * BillMate Users Seed
 */

include('includes/_env.php');
include('includes/_utilities.php');

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
$output = true;

if(file_exists(OUTPUT_DIR . '/users.json') === false){
    $allUsers = array('results' => array());

    // Fetch random users
    $fetched = 0;
    echo "Fetching $usersCount users from randomuser.me..." . PHP_EOL;
    for($i = $rounds; $i > 0; $i--, $usersCount -= $maxUsers) {
        $nextRequestCount = $i > 1 ? $maxUsers : $usersCount;
        $users = json_decode(fetchUsers($nextRequestCount), true);
        print_r($users); print $nextRequestCount; exit(1);
        foreach ($users['results'] as $user) {
            $userData = array(
                'name' => $user['user']['name']['first'] . ' ' . $user['user']['name']['last'],
                'password' => '123456',
                'c_password' => '123456',
                'email' => strpos($user['user']['email'], '.') == 0 ? substr($user['user']['email'], 1) : $user['user']['email']
            );

            $allUsers['results'][] = $userData;
        }
        $fetched += $nextRequestCount;
        echo "Fetched $fetched users from randomuser.me..." . PHP_EOL;
    }
} else {
    $allUsers = json_decode(file_get_contents(OUTPUT_DIR . '/users.json'), true);
    $countAllUsers = count($allUsers['results']);
    $output = false;
    echo "Fetching countAllUsers users from cached file..." . PHP_EOL;
}

// Add Users
$added = 0;
foreach($allUsers['results'] as $user){
    $resultStatus = sendUserData($user);

    if ($resultStatus == 302 || $resultStatus == 200) {
        echo 'User \'' . $user['name'] . '\' with email \'' . $user['email'] . '\' has been added to database. CODE: ' . $resultStatus;
        $allUsers['results'][] = $user;
        $added++;
    } else
        echo 'User \'' . $user['name'] . '\'  with email \'' . $user['email'] . '\' raised an error. CODE: ' . $resultStatus;

    echo PHP_EOL;
}

if($output)
    file_put_contents(OUTPUT_DIR . '/users.json', json_encode($allUsers));

echo 'Finished. Inserted ' + $added + ' on database' . PHP_EOL;
