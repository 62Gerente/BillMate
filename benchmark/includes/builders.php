<?php
// Build User
function getUserData(){
	$userID = '';
	$registeredID = '';

	$userData = array(
			'id' => $userID,
			'registered_id' => $registeredID,
			'email' => 'bill@mate.com',
			'password' => '123456'
		);

	return $userData;
}

function getCircleID(){
	$circleID = '';

	return $circleID;
}

function getExpenseID(){
	$expenseID = '';

	return $expenseID;
}

// Build Expense
function getExpenseData($userID){
	$value = 70;
	$circleID = '';
	$expenseTypeID = '';
	$userFriendsIDs = array('','', ''); // Note: These are user_id, not registered_user_id

	$userFriendsIDsSize = count($userFriendsIDs);
	$userFriendsValues = buildUsersValues($value, $userFriendsIDsSize);
	$expenseData = array(
		    'name' => 'YABE - Yet Another Benchmark Expense',
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
