package modele;

import java.util.ArrayList;

public class Situation {

	/**nom de la situation*/
	private String nom;

	/**nb d'instances*/
	private static int nbInstances = 0;

	/**no de l'instance*/
	private int noInstances;

	/**no de ligne*/
	private int line;
	/**no de colonne*/
	private int column;
	
	/**nb pions restant adversaire*/
	private int nbPionsRestantAdversaire;

	/**nb pions */
	private int nbPionsRestant;

	/**indique si l'etat est un etat en mode Max*/
	private boolean max = true;

	/** liste des etats accessibles a partir de l'etat courant*/
	private ArrayList<Situation> successeurs;

	/**indique si l'etat/la situation est une feuille de l'arbre*/
	private boolean feuille;

	/**indique si l'etat/la situation est une feuille d�finitive de l'arbre
	 * (i.e. impossible de cr�er des successeurs a cette situation)*/
	private boolean close;

	/**h = heuristique, estimation de la valeur de la situation*/
	private double h;

	/**grille de jeu correspondant a la situation*/
	private int[][] matriceJeu;

	/**constructeur par defaut*/
	public Situation()
	{
		noInstances = nbInstances++;
		nom=""+noInstances;
		h = 0;
		matriceJeu = new int[5][6];
		successeurs = new ArrayList<>();
	}

	/**constructeur initialisant l'estimation h 
	 * @param _h estimation de la situation*/
	public Situation(int _h)
	{
		this();
		h = _h;
	}

	/**constructeur initialisant l'estimation h et le type de noeud
	 * @param _h estimation de la situation
	 * @param _estMax determine si la valeur de la situation doit etre maximisee ou non*/
	public Situation(int _h, boolean _estMax)
	{
		this(_h);
		max = _estMax;
	}

	/**fonction evaluant la situation courante; calcul le 'h'*/
	void evaluer()
	{
		double eval = 0d;
		double coefSituation = (this.max?-1:1);
		//les valeurs positives sont pour l'IA
		// et sont diminu�es si le jeu suivant est pour l'humain
		//elles sont augment�es sinon
		double valeur = dangerPossibles();
		valeur += 0.1*coefSituation*Math.abs(valeur);
		eval+=valeur;

		h = eval;
		//		afficheMatrice();
	}
	
	private double dangerPossibles()
	{
		return 0d;
	}
	
	public void setFeuille(boolean _feuille) {
		this.feuille = _feuille;
	}

	public boolean isClose() {
		return this.close;
	}

	public boolean isFeuille() {
		return this.feuille;
	}

	public int[][] getMatriceJeu() {
		return this.matriceJeu;
	}

	public boolean isMax() {
		return this.max;
	}

	public void setColumn(int c) {
		this.column = c;
	}

	public void setLine(int l) {
		this.line = l;
	}

	public void setMatriceJeu(int[][] matrice) 
	{
		this.matriceJeu = matrice;
	}

	/**ajoute un successeur a la situation courante
	 * @param s successeur a la situation courante*/
	public void addSuccesseur(Situation s)
	{
		successeurs.add(s);
		s.nom = nom+ "" +s.nom;
	}
	
	


}
