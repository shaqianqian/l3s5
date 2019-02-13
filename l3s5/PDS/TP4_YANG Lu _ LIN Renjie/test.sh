echo "compliation"
make
echo "compliation fini"
echo "test race"
echo
echo
./race
echo
echo
echo "test forkfork"
./forkfork
echo
echo
make clean
make realclean