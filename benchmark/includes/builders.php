<?php
// Build User
function getUserData(){
	$userID = '12';
	$registeredID = '13';

	$userData = array(
			'id' => $userID,
			'registered_id' => $registeredID,
			'email' => 'bill@mate.com',
			'password' => '123456'
		);

	return $userData;
}

function getCircleID(){
	$circleID = '1641';

	return $circleID;
}

function getExpenseID(){
	$expenseID = '8830';

	return $expenseID;
}

// Build Expense
function getExpenseData($userID){
	$value = 70;
	$circleID = '1641';
	$expenseTypeID = '1644';
	$userFriendsIDs = array('214','376','12'); // Note: These are user_id, not registered_user_id

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

// Build House
function getHouseData($userID){
	$expenseTypes = array('YABET','YYABET','YYYABET');
	$houseMembers = array('70','97', '154'); // Note: These are user_id, not registered_user_id

	$houseData = array(
		    'houseName' => 'YABC - Yet Another Benchmark Circle',
		    'houseDescription' => 'Just for benchmark',
		    'expenseType' => implode(',', $expenseTypes),
		    'friendsHouse' => implode(',', $houseMembers),
		    'identifier' => $userID
		);

	return $houseData;
}

// Build Payment
function getPaymentData($userID){
	$payerID = '214';
	$expenseIDs = array('8830');
	$values = array('20');

	$paymentData = array(
		    'idsExpense' => '[' . implode(',', $expenseIDs) . ']',
		    'values' => '[' . implode(',', $values) . ']',
		    'idUser' => $payerID,
		    'flag' => 'false'
		);

	return $paymentData;
}
