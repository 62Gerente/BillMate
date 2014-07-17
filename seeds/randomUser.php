<?php
/*
 * BillMate Users Seed
 */

function fetchUsers($usersCount){
    $randomUserURL = 'http://api.randomuser.me/?results=' . $usersCount;

    $randomUser = curl_init($randomUserURL);
    curl_setopt($randomUser, CURLOPT_RETURNTRANSFER, true);
    $response = curl_exec($randomUser);
    curl_close($randomUser);

    return $response;
}

$maxUsers = 20;
$usersCount = $argv[1] ? $argv[1] : 10;
$rounds = ceil($usersCount / $maxUsers);

$allUsers = array('results' => array());
$allEmails = array();

// Fetch random users
echo "Fetching $usersCount users from randomuser.me..." . PHP_EOL;
for($fetched = $usersCount; $fetched > 0; $fetched -= $maxUsers) {
    $nextRequestCount = $fetched > 20 ? $maxUsers : $fetched;
    $users = json_decode(fetchUsers($nextRequestCount), true);
    foreach ($users['results'] as $user) {
        $email = strpos($user['user']['email'], '.') == 0 ? substr($user['user']['email'], 1) : $user['user']['email'];
        if(in_array($email, $allEmails) == FALSE) {
            $userData = array(
                'name' => ucfirst($user['user']['name']['first']) . ' ' . ucfirst($user['user']['name']['last']),
                'password' => '123456',
                'c_password' => '123456',
                'email' => $email,
                'picture' => $user['user']['picture']
            );

            $allUsers['results'][] = $userData;
        } else {
            $fetched++;
        }
    }
}
file_put_contents('database/users.json', json_encode($allUsers));

echo 'Finished. Fetched ' . count($allUsers) . ' users' . PHP_EOL;
