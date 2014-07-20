<?php

function getRequestURL($type) {
	$url = 'http://' . HOST . '/BillMate';
	switch($type) {
		case 'getDashboardUserFraction':
				$url .= '/dashboard/user';
			break;
		case 'getDashboardCircleFraction':
				$url .= '/dashboard/circle/$circleID';
			break;
		case 'getExpenseRequestFraction':
				$url .= '/expense/show/$expenseID';
			break;
		case 'postExpenseFraction':
				$url .= '/expense/save';
			break;
		case 'postCircleFraction':
				$url .= '/house/save';
			break;
		case 'postPaymentFraction':
				$url .= '/payment/confirmAll';
			break;
	}
	return $url;
}

function prepareRequest($type, $nRequests, $concurrentRequests, $input, $output) {
	$cookie = getCookieFrom($input);
	$command = '';
	switch($type) {
		case 'getDashboardUserFraction':
				$command = 'ab -k -n ' . $nRequests . ' -c ' . $concurrentRequests . ' -C ' . $cookie . ' -g ' . $output . ' ' . getRequestURL($type);
			break;
		case 'getDashboardCircleFraction':
				$command = 'ab -k -n ' . $nRequests . ' -c ' . $concurrentRequests . ' -C ' . $cookie . ' -g ' . $output . ' ' . getRequestURL($type);
			break;
		case 'getExpenseRequestFraction':
				$command = 'ab -k -n ' . $nRequests . ' -c ' . $concurrentRequests . ' -C ' . $cookie . ' -g ' . $output . ' ' . getRequestURL($type);
			break;
		case 'postExpenseFraction':
				$postFile = getExpenseDataFileNameFrom($input);
				$command = 'ab -k -n ' . $nRequests . ' -c ' . $concurrentRequests . ' -C ' . $cookie . ' -p ' . $postFile . ' -T application/x-www-form-urlencoded -g ' . $output . ' ' . getRequestURL($type);
			break;
		case 'postCircleFraction':
				$postFile = getHouseDataFileNameFrom($input);
				$command = 'ab -k -n ' . $nRequests . ' -c ' . $concurrentRequests . ' -C ' . $cookie . ' -p ' . $postFile . ' -T application/x-www-form-urlencoded -g ' . $output . ' ' . getRequestURL($type);
			break;
		case 'postPaymentFraction':
				$postFile = getPaymentDataFileNameFrom($input);
				$command = 'ab -k -n ' . $nRequests . ' -c ' . $concurrentRequests . ' -C ' . $cookie . ' -p ' . $postFile . ' -T application/x-www-form-urlencoded -g ' . $output . ' ' . getRequestURL($type);
			break;
	}

	return $command;
}

function getExpenseDataFileNameFrom($input){
	return $input . '/expense.dat';
}

function getHouseDataFileNameFrom($input){
	return $input . '/house.dat';
}

function getPaymentDataFileNameFrom($input){
	return $input . '/payment.dat';
}

function getCookieFrom($input){
	$cookie = file_get_contents($input . '/login.cookie');
	$lines = explode(PHP_EOL, $cookie);

	foreach($lines as $line) {
		if(substr_count($line, "\t") == 6) {
			$tokens = explode("\t", $line);
			$tokens = array_map('trim', $tokens);
			return $tokens[5] . '=' . $tokens[6];
		}
	}
}
