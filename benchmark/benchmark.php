<?php

define('HOST', 'localhost:8080');

include('includes/util.php');
include('includes/requests.php');
include('includes/builders.php');

// Get given parameters
if(isset($argv[1]) && $argv[1] === 'help'){
	echo 'Usage: php benchmark.php <scenario> <requests> <concurrent> <iterations>' . PHP_EOL;
	exit(1);
}

$scenario = $argc > 1 ? $argv[1] : 'default';
$requestsPerIteration = $argc > 2 ? $argv[2] : 100000;
$requestsConcurrency = $argc > 3 ? $argv[3] : 64;
$requestsIterations = $argc > 4 ? $argv[4] : 4;

// Set percentage of requests' type
$requestTypes = array(
	'getDashboardUserFraction' => 0.5,
	'getDashboardCircleFraction' => 0.15,
	'getExpenseRequestFraction' => 0.15,
	'postExpenseFraction' => 0.07,
	'postCircleFraction' => 0.03,
	'postPaymentFraction' =>  0.1
);

if($scenario !== 'default'){
	$requestTypes = json_decode(file_get_contents('scenarios/'.$scenario.'.json'), true);
}

/*
 * Begin benchmark
 */
$totalPercentage = (int) array_sum(array_values($requestTypes));
if($totalPercentage !== 1){
	echo 'Come on, check your fractions (total: ' . $totalPercentage . ')... Shame on you!' . PHP_EOL;
	exit(1);
}

// Login User
$user = getUserData();
writeLoginData($user, getcwd() . '/tmp/login.cookie');
writeExpenseData(getExpenseData($user['id']), getcwd() . '/tmp/expense.dat');
writeHouseData(getExpenseData($user['id']), getcwd() . '/tmp/house.dat');
writePaymentData(getPaymentData($user['id']), getcwd() . '/tmp/payment.dat');

for($requestIteration = 1; $requestIteration <= $requestsIterations; $requestIteration++) {
	$requestTypeIndex = chooseWithProbability(array_values($requestTypes));
	$requestType = array_keys($requestTypes)[$requestTypeIndex];

	$command = prepareRequest(
			$requestType,
			$requestsPerIteration,
			$requestsConcurrency,
			getcwd() . '/tmp',
			getcwd() . '/output/' . $requestType . '-' . $requestIteration . '-' . $requestsPerIteration . '-' . $requestsConcurrency . '.dat'
		);

	// Replace possible fields
	$command = str_replace('$expenseID', getExpenseID(), $command);
	$command = str_replace('$circleID', getCircleID(), $command);
	$command = str_replace('$id', $user['registered_id'], $command);

	echo $command, PHP_EOL; exit(1);
	exec($command);

	// Duplicate number of concurrent requests at each iteration
	$requestsConcurrency *= 2;
}
