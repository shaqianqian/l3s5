
make
echo
echo "Ma commande est: ./do --and ./test1 ./test2 ./test3"
./do --and ./test1 ./test2 ./test3
echo
echo
echo "Ma commande est: ./do -and './test1 zea e' './test2 zea eaz' './test3 je suis argument'"
./do --and "./test1 zea e" "./test2 zea eaz" "./test3 je suis argument"

echo
echo "Ma commande est: ./do --and --cc './test1 zea e' './test2 zea eaz' './test3 je suis argument'"
echo
./do --and --cc "./test1 zea e" "./test2 zea eaz" "./test3 je suis argument"
echo
echo "Ma commande est: ./do --and -cc --k './test1 zea e' './test2 zea eaz' './test3 je suis argument'"
./do --and --cc --k "./test1 zea e" "./test2 zea eaz" "./test3 je suis argument"

echo
echo
make clean
make realclean
echo