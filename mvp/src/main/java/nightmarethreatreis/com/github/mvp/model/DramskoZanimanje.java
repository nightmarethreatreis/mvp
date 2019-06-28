package nightmarethreatreis.com.github.mvp.model;

public enum DramskoZanimanje {
	GLUMAC(Glumac.class, "glumac"), 
	REZISER(Reziser.class, "reziser");
	
	private Class<? extends DramskiRadnik> klasa;
	private String naziv;
	
	private DramskoZanimanje(Class<? extends DramskiRadnik> klasa, String naziv) {
		this.klasa = klasa;
		this.naziv = naziv;
	}
	public Class<? extends DramskiRadnik> getKlasa() {
		return klasa;
	}
	public String getNaziv() {
		return naziv;
	}
	public String nazivFromClass(Class<? extends DramskiRadnik> klasa) {
		for(DramskoZanimanje zanimanje: VALUES) {
			if(zanimanje.getKlasa().equals(klasa)) {
				return zanimanje.getNaziv();
			}
		}
		return null;
	}
	
	
	private static final DramskoZanimanje[] VALUES = values();
}