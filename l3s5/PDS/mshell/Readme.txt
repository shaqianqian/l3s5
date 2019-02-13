LIN Renjie et YANG Lu

Nous avons fait tous les exercices de ce TP. C'est à dire les handlers de signal ainsi que les commandes fonctionnent bien dans notre mshell quand nous les testons.

Mais nous avons eu beaucoup de problème en réalisant la fonction do_pipe. Maintenant il y a encore un problème dans nos code: quand on exécute plusieurs commandes, si on fait ctrl Z, on ne peut pas récupérer la main et quand on fait ctrl C, il va sortir mshell. Si on teste 

mshell> head -5 a | tail -3agghrahwrwtwtwsdsfdgdhmshell> jobs[1] (13215) Foreground head[2] (13216) Foreground tailmshell>  
c’est dans foregrand

Nous sommes désolés de n'avoir pas abouti à le résoudre. 

Nous vous remercions de votre lecture et votre attention.


