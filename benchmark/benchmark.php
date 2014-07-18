<?php

define('HOST', 'localhost:8080');

include('includes/util.php');
include('includes/requests.php');
include('includes/builders.php');

// Set percentage of requests' type
$requestTypes = array(
	'getDashboardUserFraction' => 0.3,
	'getDashboardCircleFraction' => 0.15,
	'getExpenseRequestFraction' => 0.25,
	'getUserProfileFraction' => 0.1,
	'postExpenseFraction' => 0.2
);


// Get given parameters
if($argc == 1){
	echo 'Usage: php benchmark.php <requests> <concurrent> <iterations>' . PHP_EOL;
	exit(1);
}

$requestsPerIteration = $argc > 1 ? $argv[1] : 1000;
$requestsConcurrency = $argc > 2 ? $argv[2] : 50;
$requestsIterations = $argc > 3 ? $argv[3] : 5;

/*
 * Begin benchmark
 */
$totalPercentage = array_sum(array_values($requestTypes));
if($totalPercentage < 0 || $totalPercentage > 1){
	echo 'Come on, check your fractions... Shame on you!' . PHP_EOL;
	exit(1);
}

// Login User
$user = getUserData();
writeLoginData($user, getcwd() . '/tmp/login.cookie');
writeExpenseData(getExpenseData($user['id']), getcwd() . '/tmp/expense.dat');

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

	exec($command);

	// Duplicate number of concurrent requests at each iteration
	$requestsConcurrency *= 2;
}
