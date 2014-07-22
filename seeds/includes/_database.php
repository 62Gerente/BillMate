<?php
/*
 * BillMate PostgreSQL Connect
 */

function dbConnect(){
    return pg_connect("host=10.0.0.50 port=5432 dbname=billmate user=postgres");
}

function getNameFromTableWhere($resource, $table = 'registered_user', $where = '1=1'){
    return pg_fetch_all ( pg_query( 'SELECT name from ' . $table . ' WHERE ' . $where ) );
}

function getAllFromTable($resource, $table = 'registered_user'){
    return pg_fetch_all ( pg_query( 'SELECT * from ' . $table ) );
}

function getAllFromTablesJoin($resource, $table1 = 'registered_user', $table2 = 'user', $field = 'id'){
    return pg_fetch_all ( pg_query( 'SELECT * from ' . $table1 . ', ' . $table2 . ' WHERE ' . $table2 . '.' . $field . '=' . $table1 . '.' . $table2 . '_' . $field ) );
}

function getAllFromTableWhere($resource, $table = 'registered_user', $where = '1=1'){
    return pg_fetch_all ( pg_query( 'SELECT * from ' . $table . ' WHERE ' . $where ) );
}

function getAllFromTwoTablesJoin($resource, $table1 = 'registered_user', $table2 = 'user', $field1 = 'id', $field2 = 'id'){
    return pg_fetch_all ( pg_query( 'SELECT * from ' . $table1 . ', ' . $table2 . ' WHERE ' . $table2 . '.' . $field2 . '=' . $table1 . '.' . $field1 ) );
}

function getExpenseAndResponsibles($resource){
    return pg_fetch_all ( pg_query( "SELECT e.id, e.responsible_id, e.value, u.user_id FROM expense e, registered_user u WHERE e.responsible_id = u.id" ) );
}
