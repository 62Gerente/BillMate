<?php

function getUserData($email) {
	$usersInputData = json_decode(file_get_contents('output/users.json'), true);
	$users = &$usersInputData['results'];
	$totalUsers = count($users);
	$result = null;

	for ($i = 0; $result === null, $i < $totalUsers; $i++) {
		$user = $users[$i];
		if($user['email'] == $email)
			$result = $user;
	}

	return $result;
}

function getRandomUser() {
	$usersInputData = json_decode(file_get_contents('output/users.json'), true);
	$users = &$usersInputData['results'];
	$result = null;

	$position = rand(0, count($users) - 1);
	$result = $users[$position];

	return $result;
}

function getFriendsOf($friend, $n = 10) {
	$usersInputData = json_decode(file_get_contents('output/users.json'), true);
	$users = &$usersInputData['results'];
	$result = array();

	for($i = 0; $i < $n;) {
		$position = rand(0, count($users) - 1);
		$user = $users[$position];
		if($friend['email'] != $users[$position]['email']) {
			$result[] = $user['email'];
			$i++;
		}
	}

	return $result;
}

function getRandomExpenseType($n = 10) {
	$inputExpenseTypesData = json_decode(file_get_contents('database/expenseTypes.json'), true);
	$expenses = &$inputExpenseTypesData['expenseTypes'];
	$result = array();

	for($i = 0; $i < $n;) {
		$position = rand(0, count($expenses) - 1);
		$type = $expenses[$position];
		if(in_array($type, $result) == false) {
			$result[] = $type;
			$i++;
		}
	}

	return $result;
}

function sendData($data, $returnResponse = false, $url){
    $billmate = curl_init($url);
    curl_setopt($billmate, CURLOPT_RETURNTRANSFER, $returnResponse);
    curl_setopt($billmate, CURLOPT_POST, true);
    curl_setopt($billmate, CURLOPT_POSTFIELDS, $data);
    $responseData = curl_exec($billmate);
    $resultStatus = curl_getinfo($billmate, CURLINFO_HTTP_CODE);
    curl_close($billmate);

    return $returnResponse ? $responseData : $resultStatus;
}

function sendDataWithResource($data, $returnResponse = false, $url, $resource){
   	curl_setopt($resource, CURLOPT_URL, $url);
    curl_setopt($resource, CURLOPT_RETURNTRANSFER, $returnResponse);
    curl_setopt($resource, CURLOPT_POST, true);
    curl_setopt($resource, CURLOPT_POSTFIELDS, $data);
    $responseData = curl_exec($resource);
    $resultStatus = curl_getinfo($resource, CURLINFO_HTTP_CODE);

    return $returnResponse ? $responseData : $resultStatus;
}
