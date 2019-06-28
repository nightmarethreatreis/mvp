package nightmarethreatreis.com.github.mvp.managers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.scene.layout.Pane;
import nightmarethreatreis.com.github.mvp.model.Admin;
import nightmarethreatreis.com.github.mvp.model.Korisnik;
import nightmarethreatreis.com.github.mvp.model.Kupac;
import nightmarethreatreis.com.github.mvp.model.Radnik;
import nightmarethreatreis.com.github.mvp.screens.externalComponents.NavbarLink;

@Component
public class NavbarManager {
	@Autowired
	private ScreenManager screenManager;
	@Autowired
	private SessionManager sessionManager;
	
	private Map<Class<? extends Korisnik>, List<NavbarLink>> links = new HashMap<>();
	
	private NavbarLink createLink(String name, String screenName) {
		return new NavbarLink(name, event -> screenManager.activate(screenName));
	}
	
	{
		reloadNavbar();
	}
	
	public void reloadNavbar() {
		List<NavbarLink> kupacLinks = new LinkedList<>();
		List<NavbarLink> adminLinks = new LinkedList<>();
		List<NavbarLink> radnikLinks = new LinkedList<>();
	
		// KUPAC LINKS
		kupacLinks.add(createLink("Pocetna", "home"));
		kupacLinks.add(createLink("Pocetna", "home"));
		kupacLinks.add(createLink("Pocetna", "home"));
		kupacLinks.add(createLink("Pocetna", "home"));
		kupacLinks.add(createLink("Pocetna", "home"));
		kupacLinks.add(createLink("Promena korisnickog imena", "changeUsername"));
		kupacLinks.add(createLink("Promena lozinke", "changePassword"));
		
		// ADMIN LINKS
		adminLinks.add(createLink("Pocetna", "home"));
		
		// RADNIK LINKS
		radnikLinks.add(createLink("Pocetna", "home"));
		
		links.put(Kupac.class, kupacLinks);
		links.put(Admin.class, adminLinks);
		links.put(Radnik.class, radnikLinks);
	}
			
	private List<NavbarLink> getNavbarLinkList(Class<? extends Korisnik> uloga) {
		return links.get(uloga);
	}
	
	public List<NavbarLink> getNavbarLinks() {
		Korisnik korisnik = sessionManager.getLoggedInKorisnik();
		if(korisnik == null) {
			List<NavbarLink> lista = new LinkedList<>();
			lista.add(createLink("Login", "login"));
			return lista;
		}
		return getNavbarLinkList(korisnik.getClass());
	}
	
	public void updateNavbar(Pane navbar) {
		if(navbar == null) {
			throw new NullPointerException("Navbar ne sme biti null");
		}
		navbar.getChildren().clear();
		navbar.getChildren().addAll(getNavbarLinks());
	}
}
