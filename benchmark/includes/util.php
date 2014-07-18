<?php
// Login Billmate
function writeLoginData($data, $cookieFile){
    $loginURL = 'http://' . HOST . '/BillMate/session/save';
    $cookieFile = 'tmp/' . 'login.cookie';

    $curl = curl_init($loginURL);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
    if(!file_exists($cookieFile)) {
	    touch($cookieFile);
	  }
    file_put_contents($cookieFile, '');
    curl_setopt($curl, CURLOPT_COOKIEJAR, $cookieFile);
    curl_exec($curl);
    curl_setopt($curl, CURLOPT_POST, false);

    return $curl;
}

// Random Expense Billmate
function writeExpenseData($expenseData, $output){
    $toFind = array('%5B\d%');
    $toReplace = array('%5B%');
    $query = str_replace('%%', '%', preg_replace($toFind, $toReplace, http_build_query($expenseData)));

    if(file_exists($output) === FALSE) {
      touch($output);
    }

    file_put_contents($output, $query);
}

// Chooses type of request by geometrical probability:
//
//                  0.75 0.9
//                   |    |
//                   V    V
// *--------*--*-----*-*--*--* <-- (length)
// ^        ^  ^       ^     ^
// |        |  |       |     |
// 0      0.4 0.5     0.8    1
function chooseWithProbability(array $types, $length = 10000)
{
   $position = 0;
   $result = 0;
   foreach($types as $i => $probability) {
      $types[$i] = $position + $probability * $length;
      $position = $types[$i];
   }
   $test = mt_rand(1, $length);
   $position = 1;
   foreach($types as $i => $probability) {
      if($test >= $position && $test <= $probability) {
         $result = $i;
         break;
      }
      $position = $probability;
   }
   return $result;
}
