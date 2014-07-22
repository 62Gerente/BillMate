#! /bin/bash

echo "Prepare..."
php benchmark.php 0 100 16 1
echo "Prepare to hit!"

sleep 5

echo "START BENCHMARK $1"
php benchmark.php $1
echo "END BENCHMARK $1"

#echo "START BENCHMARK 1"
#php benchmark.php 2
#echo "END BENCHMARK 2"
#
#echo "START BENCHMARK 3"
#php benchmark.php 3
#echo "END BENCHMARK 3"
#
#echo "START BENCHMARK 4"
#php benchmark.php 4
#echo "END BENCHMARK 4"
