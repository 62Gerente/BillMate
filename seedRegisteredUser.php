<?php
/*
 * BillMate Users Seed
 */
$usersCount = $argv[1] ? $argv[1] : 10;

// Set URL's
$randomUserURL = 'http://api.randomuser.me/?results=' . $usersCount;
$billmateURL = 'http://localhost:8080/BillMate/register/save';

// Fetch random users
$randomUser = curl_init($randomUserURL);
curl_setopt($randomUser, CURLOPT_RETURNTRANSFER, true);
$response = curl_exec($randomUser);
curl_close($randomUser);

$added = 0;
$users = json_decode($response, true);
foreach ($users['results'] as $user) {
    $userData = array(
        'name' => $user['user']['name']['first'] . ' ' . $user['user']['name']['last'],
        'password' => $user['user']['password'],
        'c_password' => $user['user']['password'],
        'email' => $user['user']['email']
    );

    // Add User
    $billmate = curl_init($billmateURL);
    curl_setopt($billmate, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($billmate, CURLOPT_POST, true);
    curl_setopt($billmate, CURLOPT_POSTFIELDS, $userData);
    curl_exec($billmate);
    $resultStatus = curl_getinfo($billmate, CURLINFO_HTTP_CODE);
    curl_close($billmate);

    if ($resultStatus == 302 || $resultStatus == 200) {
        echo 'User \'' . $userData['name'] . '\' has been added to database. CODE: ' . $resultStatus;
        $added++;
    } else
        echo 'User \'' . $userData['name'] . '\' raised an error. CODE: ' . $resultStatus;

    echo PHP_EOL;
}

echo 'Finished. Inserted ' + $added + ' on database' . PHP_EOL;
