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
		case 'getUserProfileFraction':
				$url .= '/registeredUser/edit/$id';
			break;
		case 'postExpenseFraction':
				$url .= '/expense/save';
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
		case 'getUserProfileFraction':
				$command = 'ab -k -n ' . $nRequests . ' -c ' . $concurrentRequests . ' -C ' . $cookie . ' -g ' . $output . ' ' . getRequestURL($type);
			break;
		case 'postExpenseFraction':
				$postFile = getExpenseDataFileNameFrom($input);
				$command = 'ab -k -n ' . $nRequests . ' -c ' . $concurrentRequests . ' -C ' . $cookie . ' -P ' . $postFile . ' -g ' . $output . ' ' . getRequestURL($type);
			break;
	}

	return $command;
}

function getExpenseDataFileNameFrom($input){
	return $input . '/expense.dat';
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
