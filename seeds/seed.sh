#!/bin/bash
# BillMate seed

php seedRegisteredUser.php 100
sed -i.pw.bak 's/"8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92"/"123456"/g' output/users.json
php seedCircle.php 40
php seedExpense.php 4
php seedPayment.php
