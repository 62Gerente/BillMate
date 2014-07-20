<?php
/*
 * BillMate Payment Seed
 */

include('includes/_env.php');
include('includes/_loginUser.php');
include('includes/_utilities.php');
include('includes/_database.php');

function chooseRandomDebt($debts){
    if(count($debts) == 1)
        $nDebts = 1;
    else
        $nDebts = rand(1, count($debts));

    $debtsOut = array();
    for($i = 0; $i < $nDebts; $i++){
        $position = rand(0, count($debts) - 1);
        $debtsOut[] = $debts[$position];
    }

    return $debtsOut;
}

function sendDebtData($data, $returnResponse = false, $resource){
    return sendDataWithResource($data, $returnResponse, 'http://' . HOST . '/BillMate/payment/confirmAll', $resource);
}

$inputExpenseData = json_decode(file_get_contents('output/expense.json'), true);
$allDebt = array();


// Build Debt
$allExpenses = $inputExpenseData;
$totalExpense = count($allExpenses);

$allDebtCount = 0;
for($i = 0; $i < $totalExpense; $i++){
    $expenseID = $allExpenses[$i]['id'];
    $debts = chooseRandomDebt($allExpenses[$i]['debt']);
    $responsible_uid = $allExpenses[$i]['user_id'];
    $expenseData = array();
    $debtCount = count($debts);
    for($j = 0; $j < $debtCount; $j++) {
        $userID = $debts[$j]['user_id'];

        if(empty($expenseData[$userID])){
            $expenseData[$userID] = array();
            $toConfirm = $expenseData[$userID];
        } else {
            $toConfirm = $expenseData[$userID];
        }

        $toConfirm['idsExpense'][] = $debts[$j]['expense_id'];
        $toConfirm['values'][] = $debts[$j]['value'];

        $userId = $debts[$j]['user_id'];
        $expenseData[$userId] = $toConfirm;

        $allDebt[$responsible_uid] = $expenseData;
    }
    $allDebtCount += $debtCount;
}

// Add Expense
echo "Confirming $allDebtCount..." . PHP_EOL;
foreach($allDebt as $responsible_uid => $expense){
    $userData = getUserData($responsible_uid);
    $resource = login($userData, $userData['name']);
    foreach($expense as $payerID => $debt){
        @$debt['idUser'] = $payerID;
        @$debt['flag'] = 'false';
        $debt['idsExpense'] = '['.implode(',', $debt['idsExpense']).']';
        $debt['values'] = '['.implode(',', $debt['values']).']';
        $resultStatus = strpos(sendDebtData(http_build_query($debt), true, $resource), "alert-error") === FALSE;
    }
    curl_close($resource);
}

echo 'Finished.' . PHP_EOL;
