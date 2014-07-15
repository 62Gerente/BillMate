<?php
/*
 * BillMate Login
 */

function login($data, $user = '_default'){
    $billmateURL = 'http://' . HOST . '/BillMate/session/save';
    $cookieFile = COOKIE_DIR . '/' . $user . '.cookie';

    $billmate = curl_init($billmateURL);
    curl_setopt($billmate, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($billmate, CURLOPT_POST, true);
    curl_setopt($billmate, CURLOPT_POSTFIELDS, $data);
    if(file_exists($cookieFile)) {
	    curl_setopt($billmate, CURLOPT_COOKIEFILE, $cookieFile);
    } else {
	    touch($cookieFile);
	    curl_setopt($billmate, CURLOPT_COOKIEJAR, $cookieFile);
	}
    curl_exec($billmate);
    curl_setopt($billmate, CURLOPT_POST, false);

    return $billmate;
}
