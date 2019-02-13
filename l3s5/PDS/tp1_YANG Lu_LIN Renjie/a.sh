echo /*Test*/
echo "**********************"
make
echo
echo
echo "on va tester ./prlimit"
./prlimit
echo
echo
echo "./maccess -v qsd"
./maccess -v qsd
make clean
make realclean