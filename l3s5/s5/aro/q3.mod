set VEG;
param consommation{VEG}>= 0;
param rendement{VEG} >= 0;
param prix_vente {VEG} >= 0;
param vente_max {VEG} >= 0;
param are :=150;
var qte_mouton integer>=0;
var are_veg {v in VEG} >= 0 ;
var hanger_used = qte_mouton/29;

var hanger_not_used=5-hanger_used;
subject to are_limitee :
(sum {v in VEG} are_veg [v]) + qte_mouton*2 <=are;
subject to limit_mouton_con {v in VEG}:
are_veg[v]*rendement[v] >= consommation[v]*qte_mouton; 
subject to limit_vent_max {v in VEG}:
are_veg[v]*rendement[v]-consommation[v]*qte_mouton <= vente_max[v];
maximize profit :
(sum {v in VEG} ((are_veg[v]*rendement[v]-consommation[v]*qte_mouton) * prix_vente[v]))+(qte_mouton*200)+(hanger_not_used*1500);
data;
set VEG := betterave ble mais  ;


param: rendement consommation vente_max prix_vente:=
betterave
         1         0.8         10       100
ble
         0.6       0.1         20       120
mais
         0.5       0.3         20       90 ;