<?php
/*
 * BillMate Circles Seed
 */

include('includes/_env.php');
include('includes/_loginUser.php');
include('includes/_utilities.php');

function sendHouseData($data, $returnResponse = false, $resource){
    return sendDataWithResource($data, $returnResponse, 'http://' . HOST . '/BillMate/house/save', $resource);
}

function sendCollectiveData($data, $returnResponse = false, $resource){
    return sendDataWithResource($data, $returnResponse, 'http://' . HOST . '/BillMate/collective/save', $resource);
}

$circlesCount = $argv[1] ? $argv[1] : 10;
$inputCirclesData = json_decode(file_get_contents('database/circles.json'), true);
$inputExpenseTypes = json_decode(file_get_contents('database/expenseTypes.json'), true);
$allCircles = array('results' => array());

// Build Circles
$fetched = 0;
for($i = 0; $i < $circlesCount; $i++) {
    $user = getRandomUser();
    $type = rand(0, 5000) < 2500 ? 'collective' : 'house';
    $position = rand(0, count($inputCirclesData[$type]) - 1);
    $circleData = array(
        ($type . 'Name') => $inputCirclesData[$type][$position],
        ('friends' . ucfirst($type)) => implode( ',' , getFriendsOf($user, rand(1, 20))),
        'expenseType' => implode( ',' , getRandomExpenseType(rand(1, 6)))
    );

    $allCircles['results'][$user['email']][$type][] = $circleData;
}

// Add Circles
$added = 0;
$addedCircles = array('results' => array());
echo "Inserting $circlesCount circles..." . PHP_EOL;
foreach($allCircles['results'] as $user => $types){
    $userData = getUserData($user);
    $resource = login($userData, $userData['name']);
    foreach ($types as $typeName => $circles) {
        foreach($circles as $circle){
            if($typeName == 'collective'){
                $resultStatus = strpos(sendCollectiveData($circle, true, $resource), "alert-error") === FALSE;
            } else {
                $resultStatus = strpos(sendHouseData($circle, true, $resource), "alert-error") === FALSE;
            }

            if ($resultStatus) {
                echo 'Circle \'' . $circle[$typeName . 'Name'] . '\' has been added to database.';
                $addedCircles['results'][$user][$typeName][] = $circle;
                $added++;
            } else {
                echo 'Circle \'' . $circle[$typeName . 'Name'] . '\' raised an error';
            }

            echo PHP_EOL;
        }
    }
    curl_close($resource);
}

file_put_contents(OUTPUT_DIR . '/circles.json', json_encode($addedCircles));

echo 'Finished. Inserted ' + $added + ' on database' . PHP_EOL;
