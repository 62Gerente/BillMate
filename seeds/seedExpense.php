<?php
/*
 * BillMate Expense Seed
 */

include('includes/_env.php');
include('includes/_loginUser.php');
include('includes/_utilities.php');
include('includes/_database.php');

function chooseRandomUsersAndValuesWithout($value, $users, $userID){
    $nUsers = rand(1, count($users) - 1);
    $eqValue = $value / $nUsers;

    $userCircles = array();
    $valueUsers = array();
    for($i = 0; $i < $nUsers;){
        $position = rand(0, count($users) - 1);
        if($users[$position]['user_id'] != $userID){
            $userCircles[] = $users[$position]['user_id'];
            $valueUsers[] = $eqValue;
            $i++;
        }
    }

    return array('user' => $userCircles, 'value' => $valueUsers);
}

function sendExpenseData($data, $returnResponse = false, $resource){
    return sendDataWithResource($data, $returnResponse, 'http://' . HOST . '/BillMate/expense/save', $resource);
}

$expenseCount = $argv[1] ? $argv[1] : 10;
$inputExpenseData = json_decode(file_get_contents('output/circles.json'), true);
$allExpense = array();


// Build Expense
$fetched = 0;
$allCircles = array_merge($inputExpenseData['house'], $inputExpenseData['collective']);
$totalCircle = count($allCircles);

for($i = 0; $i < $totalCircle; $i++){
    $circleID = $allCircles[$i]['id'];
    $users = $allCircles[$i]['users'];
    $types = $allCircles[$i]['expenseTypes'];
    if($types !== false){
        for($j = 0; $j < $expenseCount; $j++) {
            $user = getRandomUserDataFromArray($users);
            $userEmail = $user['email'];
            $expenseTypeID = getRandomExpenseTypeDataFromArray($types);
            $value = rand(1, 64);
            $expenseDetails = chooseRandomUsersAndValuesWithout($value, $users, $user['user_id']);
            $expenseName = getNameFromTableWhere(dbConnect(), 'expense_type', 'id=' . $expenseTypeID);
            $expenseData = array(
                'name' => $expenseName[0]['name'] . ' Expense',
                'idCircle' => $circleID,
                'idExpenseType' => $expenseTypeID,
                'description' => 'A long and informative description for current expense. Basically, pay everything or I will kill you',
                'idUser' => $user['user_id'],
                'value' => $value,
                'listOfFriends' => $expenseDetails['user'],
                'listValuesUsers' => $expenseDetails['value'],
                'numberSelected' => count($expenseDetails['user']),
                'beginDate' => '',
                'endDate' => '',
                'receptionDate' => '',
                'receptionDeadline' => '',
                'paymentDeadline' => '',
                'paymentDate' => ''
            );

            $allExpense[] = $expenseData;
        }
    }
}

// Add Expense
echo "Inserting $expenseCount expense..." . PHP_EOL;
foreach($allExpense as $expense){
    $userData = getUserData($expense['idUser']);
    $resource = login($userData, $userData['name']);
    $toFind = array('%5B\d%');
    $toReplace = array('%5B%');
    $query = str_replace('%%', '%', preg_replace($toFind, $toReplace, http_build_query($expense)));
    $resultStatus = strpos(sendExpenseData($query, true, $resource), "alert-error") === FALSE;
    curl_close($resource);
}

// All Database Expense
$addedExpense = getAllFromTwoTablesJoin(dbConnect(), 'expense', 'registered_user', 'responsible_id', 'id');

file_put_contents(OUTPUT_DIR . '/expense.json', json_encode($addedExpense));

echo 'Finished. There are ' . count($addedExpense) . ' on database' . PHP_EOL;
