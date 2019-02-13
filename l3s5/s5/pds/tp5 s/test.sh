
make
echo
echo "Ma commande est: ./do -a ./test1 ./test2 ./test3"
./args -a ./test1 ./test2 ./test3
echo

echo "Ma commande est: ./do -o -c './test1 zea e' './test2 zea eaz' './test3 je suis argument'"
echo
./args -a -cc "./test1 zea e" "./test2 zea eaz" "./test3 je suis argument"
echo
echo "Ma commande est: ./do --and -cc --k './test1 zea e' './test2 zea eaz' './test3 je suis argument'"
./args -a -c -k "./test1 zea e" "./test2 zea eaz" "./test3 je suis argument"

echo
echo
make clean
make realclean
echo
