/* variable qui contiendra la commande */
static char cmd[1024];

/* build_cmdline : construit la ligne de commande */
void build_cmdline(char *cmds[MAXCMDS][MAXARGS],int nbcmd,int bg) {
	int i;
	int j;

	strcpy(cmdline,"");
	j = 0;
	for (i = 0 ; i < nbcmd ; i++) {
		while (cmds[i][j]) {
			strcat(cmd,cmds[i][j]);
			strcat(cmd," ");
			j++;
		}
		(i != nbcmd - 1) ? strcat(cmdline,"| ") : strcat(cmdline,"");
		j = 0;
	}

	/* quand la commande est en arrière-plan, on rajoute l'esperluette à sa fin */
	(bg) ? strcat(cmdline," &") : 0;
}

/* reset_signals : remet les signaux à "zéro" */
int reset_signals() {
	struct sigaction sa;

	sa.sa_handler = SIG_DFL;
	sigemptyset(&sa.sa_mask);
	sa.sa_flags = 0;

	sigaction(SIGCHLD,&sa,NULL);
	sigaction(SIGINT,&sa,NULL);
	sigaction(SIGTSTP,&sa,NULL);
	
	return 0;
}

