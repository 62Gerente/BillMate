<?php
// Build User
function getUserData(){
	$userID = '13';

	$userData = array(
			'id' => $userID,
			'email' => 'bill@mate.com',
			'password' => '123456'
		);
}

function getCircleID(){
	$circleID = '298';

	return $circleID;
}

function getExpenseID(){
	$expenseID = '7201';

	return $expenseID;
}

// Build Expense
function getExpenseData($userID){
	$value = 70;
	$circleID = '298';
	$expenseTypeID = '301';
	$userFriendsIDs = array('143','71', '185');

	$userFriendsIDsSize = count($userFriendsIDs);
	$userFriendsValues = buildUsersValues($value, $userFriendsIDsSize);
	$expenseData = array(
		    'name' => 'Benchmark Expense',
		    'idCircle' => $circleID,
		    'idExpenseType' => $expenseTypeID,
		    'description' => 'Just for benchmark',
		    'idUser' => $userID,
		    'value' => $value,
		    'listOfFriends' => $userFriendsIDs,
		    'listValuesUsers' => $userFriendsValues,
		    'numberSelected' => $userFriendsIDsSize,
		    'beginDate' => '',
		    'endDate' => '',
		    'receptionDate' => '',
		    'receptionDeadline' => '',
		    'paymentDeadline' => '',
		    'paymentDate' => ''
		);

	return $expenseData;
}

function buildUsersValues($value, $size){
	$userFriendsValues = array();
	$valueForEachUser = $value / $size;
	for($i = 0; $i < $size; $i++){
		$userFriendsValues[] = $valueForEachUser;
	}

	return $userFriendsValues;
}
