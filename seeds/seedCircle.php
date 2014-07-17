<?php
/*
 * BillMate Circles Seed
 */

include('includes/_env.php');
include('includes/_loginUser.php');
include('includes/_utilities.php');
include('includes/_database.php');

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
        ('friends' . ucfirst($type)) => implode( ',' , getFriendsOf($user, rand(2, 20))),
        'expenseType' => implode( ',' , getRandomExpenseType(rand(1, 6)))
    );

    $allCircles['results'][$user['email']][$type][] = $circleData;
}

// Add Circles
$added = 0;
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
                $added++;
            } else {
                echo 'Circle \'' . $circle[$typeName . 'Name'] . '\' raised an error';
            }

            echo PHP_EOL;
        }
    }
    curl_close($resource);
}

// All Database Circles
$addedCircles = array(
        'house' => getAllFromTablesJoin(dbConnect(), 'house', 'circle', 'id'),
        'collective' => getAllFromTablesJoin(dbConnect(), 'collective', 'circle', 'id')
    );

foreach($addedCircles['house'] as &$house){
    $house['users'] = getAllFromTableWhere(dbConnect(), 'circle_users', 'circle_id=' . $house['circle_id']);
    $house['expenseTypes'] = getAllFromTableWhere(dbConnect(), 'circle_expense_types', 'circle_id=' . $house['circle_id']);
}

foreach($addedCircles['collective'] as &$collective){
    $collective['users'] = getAllFromTableWhere(dbConnect(), 'circle_users', 'circle_id=' . $collective['circle_id']);
    $collective['expenseTypes'] = getAllFromTableWhere(dbConnect(), 'circle_expense_types', 'circle_id=' . $collective['circle_id']);
}

file_put_contents(OUTPUT_DIR . '/circles.json', json_encode($addedCircles));

echo 'Finished. There are ' . (count($addedCircles['house']) + count($addedCircles['collective'])) . ' on database' . PHP_EOL;
