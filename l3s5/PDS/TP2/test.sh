echo "compilation\n"
make prog
echo "\ntest de mdu:"
./mdu

echo 
echo "\nPour tester un lien sympolique"
echo "\nMon commande: ./mdu fdsfds/dsa"
./mdu -b fdsfds/dsa

echo 
echo "\nPour tester un fichier"
echo "\nMon commande: ./mdu fdsfds/gfd"
./mdu -b fdsfds/gfd

echo 
echo "\nPour tester un répertoire"
echo "\nMon commande: ./mdu fdsfds/dsq"
./mdu -b fdsfds/dsq

echo 
echo "\nSans options"
echo "\nMon commande: ./mdu fdsfds "
./mdu fdsfds

echo 
echo "\nIndiquant de suivre les liens symboliques"
echo "\nMon commande: ./mdu -L fdsfds "
./mdu -L fdsfds

echo 
echo "\nIndiquant de rapporter les tailles apparentes"
echo "\nMon commande: ./mdu -b fdsfds "
./mdu -b fdsfds

echo "\n\n\nTous les tests sont finis!"
echo "\nTP2 Gr4 Shichen ZHAO et Tianjun Lin"
echo "\nMerci à votre attention et lecture. Nous vous souhaitons une bonne journée! \n"

